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

class DatabaseManagerImpl : DatabaseManager {
    override fun setupDatabase() {
    }

    override fun getPlayerId(uuid: UUID): Int {
        TODO()
    }

    override fun getPlayerId(name: String): Int {
        TODO()
    }

    override fun getWorldId(uuid: UUID): Int {
        TODO()
    }

    override fun getWorldId(name: String): Int {
        TODO()
    }

    override fun close() {
        TODO()
    }

    override val connection: Connection
        get() = throw UnsupportedOperationException()
}
