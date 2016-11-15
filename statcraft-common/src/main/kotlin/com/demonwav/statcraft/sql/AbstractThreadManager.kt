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

    private val CHAIN = "statcraft_shared_chain"

    override fun <T : Any> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        StatCraft.getInstance().taskChain.newSharedChain<T>(CHAIN)
            .asyncFirst { StatCraft.getInstance().databaseManager.getFirstColumn<T>(query, *params) }
            .sync { result -> future.complete(result) }
            .execute()
        return future
    }

    override fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?) {
        StatCraft.getInstance().taskChain.newSharedChain<Any>(CHAIN)
            .async { -> StatCraft.getInstance().databaseManager.executeUpdate(query, *params) }
    }

    override fun scheduleAsync(runnable: Runnable) {
        StatCraft.getInstance().taskChain.newSharedChain<Any>(CHAIN)
            .async(runnable::run)
    }

    override fun scheduleMain(runnable: Runnable) {
        StatCraft.getInstance().taskChain.newSharedChain<Any>(CHAIN)
            .sync(runnable::run)
    }

    override fun close() {
        StatCraft.getInstance().taskChain.shutdown(20, TimeUnit.MILLISECONDS)
    }
}
