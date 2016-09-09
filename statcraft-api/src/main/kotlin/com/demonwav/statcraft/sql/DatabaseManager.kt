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
 * The main database API for StatCraft. This handles initializing and setting up the database, making queries, and
 * handling the connections.
 */
interface DatabaseManager : AutoCloseable {

    /**
     * Retrieve a new [Connection] instance from the connection pool.
     *
     * @return A new [Connection] instance from the connection pool.
     */
    val connection: Connection

    /**
     * Setup the connection pool data source and connect to the database. If this fails then StatCraft will be
     * disabled.
     */
    fun initialize()

    /**
     * Check all tables and verify database structure is correct. Also handles database conversions when the layout
     * changes.
     */
    fun setupDatabase()

    /**
     * Using the MySQL query string, create a [DbStatement]. This DbStatement must be closed either in a finally block
     * or using a try-with-resources.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @return A new [DbStatement] from the given query string.
     */
    fun query(@Language("MySQL") query: String): DbStatement

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the update statement
     * without returning anything.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     */
    fun execute(@Language("MySQL") query: String, vararg params: Any?)

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the query and return
     * the first row returned, or null if nothing is returned. This ignores all other rows that are returned.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     * @return The [DbRow] returned from the query, or null if nothing is returned.
     */
    fun getFirstRow(@Language("MySQL") query: String, vararg params: Any?): DbRow?

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the query and
     * return the value of the first column of the first row, or null if nothing is returned. This ignores all other
     * rows and columns that are returned.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     * @return The value of the first column of the first row returned from the query, or null if nothing is returned.
     */
    fun <T> getFirstColumn(@Language("MySQL") query: String, vararg params: Any?): T?

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the query and
     * return a list of the values of the first column of every row, or an empty list if nothing is returned. This
     * ignores all other columns that are returned.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     * @return A list of the values of the first column of every row, or an empty list if nothing is returned.
     *         Never null.
     */
    fun <T> getFirstColumnResults(@Language("MySQL") query: String, vararg params: Any?): List<T>

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the query and
     * return a list of all the rows, or an empty list if nothing is returned.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     * @return A list of the [DbRow]'s, or an empty list if nothing is returned. Never null.
     */
    fun getResults(@Language("MySQL") query: String, vararg params: Any?): List<DbRow>

    /**
     * Using the MySQL query string and the optional given parameters for the prepared statement, execute the update
     * and return the first auto-generated id created by the update, or null if none were created.
     *
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param query The MySQL string to make up the prepared statement
     * @param params Array of objects for inserting into the prepared statement
     * @return The first auto-generated id created by the update, or null if none were created.
     */
    fun executeUpdate(@Language("MySQL") query: String, vararg params: Any?): Long?

    /**
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param uuid The player's [UUID]
     * @return The int id of the player, or null if not found.
     */
    fun getPlayerId(uuid: UUID): Int?
    /**
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param name The player's name
     * @return The int id of the player, or null if not found.
     */
    fun getPlayerId(name: String): Int?

    /**
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param uuid The world's [UUID]
     * @return The int id of the world, or null if not found.
     */
    fun getWorldId(uuid: UUID): Int?
    /**
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param name The world's name
     * @return The int id of the world, or null if not found.
     */
    fun getWorldId(name: String): Int?

    /**
     * This method will run on the thread it is called and may be blocking. It should never be run on the main thread,
     * use the [ThreadManager] instance for this.
     *
     * @param uuid The plugin's [UUID]
     * @return The int id of the plugin, or null if not found.
     */
    fun getPluginId(uuid: UUID): Int?
}
