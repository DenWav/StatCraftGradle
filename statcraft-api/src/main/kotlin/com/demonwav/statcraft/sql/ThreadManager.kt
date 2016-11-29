/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import org.intellij.lang.annotations.Language
import java.util.concurrent.CompletableFuture

/**
 * This is mostly a convenience class for scheduling database queries. The recommended way to implement this is with TaskChain, though
 * it is not required.
 */
interface ThreadManager : AutoCloseable {

    /**
     * Kill all running tasks and threads, within 50 ms (the time of 1 tick).
     */
    fun shutdown()

    /**
     * Schedule a given MySQL query to be run on an async thread, with the given parameters, returning a [CompletableFuture] of the
     * specified type.
     *
     * @param query The MySQL query to run.
     * @param params The parameters to apply to the query, if there are any.
     * @return A completable future of the given type, which will be completed when the query finishes.
     */
    fun <T : Any> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): CompletableFuture<T>

    /**
     * Schedule a given MySQL query to be run on an async thread, with the given parameters, not returning anything.
     *
     * @param query The MySQL query to run.
     * @param params The parameters to apply to the query, if there are any.
     */
    fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?)
}
