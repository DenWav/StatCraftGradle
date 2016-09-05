/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

/**
 * TODO
 */
interface Promise<T> {

    /**
     * TODO
     */
    fun setValue(t: T?)

    /**
     * TODO
     */
    fun setError(message: String)

    /**
     * TODO
     */
    fun done(work: (T?) -> Unit)
}
