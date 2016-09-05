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
import java.util.concurrent.ConcurrentLinkedQueue

abstract class AbstractThreadManager : ThreadManager {

    private val asyncQueue = ConcurrentLinkedQueue<Runnable>()
    private val mainQueue = ConcurrentLinkedQueue<Runnable>()

    abstract fun startExecutors()

    override fun <T> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): Promise<T> {
        val promise = PromiseImpl<T>()
        scheduleAsync(Runnable {
            promise.setValue(StatCraft.instance.databaseManager.getFirstColumn<T>(query, *params))
        })
        return promise
    }

    override fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?) {
        scheduleAsync(Runnable {
            StatCraft.instance.databaseManager.executeUpdate(query, *params)
        })
    }

    override fun scheduleAsync(runnable: Runnable) {
        asyncQueue.offer(runnable)
    }

    override fun scheduleMain(runnable: Runnable) {
        mainQueue.offer(runnable)
    }

    override val async: Runnable by lazy {
        Async()
    }

    override val main: Runnable by lazy {
        Main()
    }

    override fun close() {
        // Do all work that's left immediately
        async.run()
        main.run()
    }

    inner abstract class WorkingRunnable : Runnable {
        abstract fun getQueue(): ConcurrentLinkedQueue<Runnable>

        override fun run() {
            var runnable = getQueue().poll()
            while (runnable != null) {
                try {
                    runnable.run()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                runnable = getQueue().poll()
            }
        }
    }

    inner class Async : WorkingRunnable() {
        override fun getQueue() = asyncQueue
    }

    inner class Main : WorkingRunnable() {
        override fun getQueue() = mainQueue
    }
}
