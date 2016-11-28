/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.demonwav.statcraft.StatCraft
import org.intellij.lang.annotations.Language
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

abstract class AbstractThreadManager : ThreadManager {

    override fun <T : Any> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        StatCraft.newChain()
            .asyncFirst { StatCraft.getInstance().databaseManager.getFirstColumn<T>(query, *params) }
            .sync { result -> future.complete(result) }
            .execute()
        return future
    }

    override fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?) {
        StatCraft.newChain()
            .async() { -> StatCraft.getInstance().databaseManager.executeUpdate(query, *params) }
            .execute()
    }

    override fun close() {
        StatCraft.getInstance().taskChain.shutdown(20, TimeUnit.MILLISECONDS)
    }
}
