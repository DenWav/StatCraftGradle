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
        StatCraft.newSharedChain()
            .asyncFirst { StatCraft.getInstance().databaseManager.getFirstColumn<T>(query, *params) }
            .sync { result -> future.complete(result) }
            .execute()
        return future
    }

    override fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?) {
        StatCraft.newSharedChain()
            .async { -> StatCraft.getInstance().databaseManager.executeUpdate(query, *params) }
            .execute()
    }

    override fun scheduleAsync(runnable: Runnable) {
        StatCraft.newSharedChain()
            .async(runnable::run)
            .execute()
    }

    override fun scheduleMain(runnable: Runnable) {
        StatCraft.newSharedChain()
            .sync(runnable::run)
            .execute()
    }

    override fun close() {
        StatCraft.getInstance().taskChain.shutdown(20, TimeUnit.MILLISECONDS)
    }
}
