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
 * TODO
 */
interface ThreadManager : AutoCloseable {

    /**
     * TODO
     */
    fun shutdown()

    /**
     * TODO
     */
    fun <T : Any> scheduleQuery(@Language("MySQL") query: String, vararg params: Any?): CompletableFuture<T>

    /**
     * TODO
     */
    fun scheduleUpdate(@Language("MySQL") query: String, vararg params: Any?)
}
