/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.demonwav.statcraft.Promise
import org.intellij.lang.annotations.Language

/**
 * TODO
 */
interface ThreadManager : AutoCloseable {

    /**
     * TODO
     */
    fun startExecutors()

    /**
     * TODO
     */
    fun shutdown()

    /**
     * TODO
     */
    fun <T> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): Promise<T>

    /**
     * TODO
     */
    fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?)

    /**
     * TODO
     */
    fun scheduleAsync(runnable: Runnable)

    /**
     * TODO
     */
    fun scheduleMain(runnable: Runnable)

    /**
     * TODO
     */
    val async: Runnable
    /**
     * TODO
     */
    val main: Runnable
}
