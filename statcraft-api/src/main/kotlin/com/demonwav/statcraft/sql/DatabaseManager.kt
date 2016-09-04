/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import java.sql.Connection
import java.util.UUID

/**
 * TODO
 */
interface DatabaseManager : AutoCloseable {

    /**
     * TODO
     */
    val connection: Connection

    /**
     * TODO
     */
    fun setupDatabase()

    /**
     * TODO
     */
    fun getPlayerId(uuid: UUID): Int
    /**
     * TODO
     */
    fun getPlayerId(name: String): Int?

    /**
     * TODO
     */
    fun getWorldId(uuid: UUID): Int
    /**
     * TODO
     */
    fun getWorldId(name: String): Int
}
