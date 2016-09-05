/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.empireminecraft.systems.db.DbRow
import com.empireminecraft.systems.db.DbStatement
import org.intellij.lang.annotations.Language
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
    fun query(@Language("MySQL") query: String): DbStatement

    /**
     * TODO
     */
    fun getFirstRow(@Language("MySQL") query: String, vararg params: Any): DbRow?

    /**
     * TODO
     */
    fun <T> getFirstColumn(@Language("MySQL") query: String, vararg params: Any): T?

    /**
     * TODO
     */
    fun <T> getFirstColumnResults(@Language("MySQL") query: String, vararg params: Any): List<T>?

    /**
     * TODO
     */
    fun getResults(@Language("MySQL") query: String, vararg params: Any): List<DbRow>?

    /**
     * TODO
     */
    fun executeUpdate(@Language("MySQL") query: String, vararg params: Any): Int

    /**
     * TODO
     */
    fun getPlayerId(uuid: UUID): Int?
    /**
     * TODO
     */
    fun getPlayerId(name: String): Int?

    /**
     * TODO
     */
    fun getWorldId(uuid: UUID): Int?
    /**
     * TODO
     */
    fun getWorldId(name: String): Int?
}
