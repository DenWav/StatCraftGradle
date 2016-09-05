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
import com.demonwav.statcraft.toByte
import com.demonwav.statcraft.use
import com.empireminecraft.systems.db.DbRow
import com.empireminecraft.systems.db.DbStatement
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.intellij.lang.annotations.Language
import org.mariadb.jdbc.MariaDbDataSource
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class AbstractDatabaseManager : DatabaseManager {
    private lateinit var dataSource: HikariDataSource

    private val playerMap = ConcurrentHashMap<UUID, Int>()
    private val worldMap = ConcurrentHashMap<UUID, Int>()
    private val pluginMap = ConcurrentHashMap<UUID, Int>()

    init {
        try {
            val config = HikariConfig()
            config.poolName = "StatCraftDb"

            config.dataSourceClassName = MariaDbDataSource::class.java.name
            config.username = StatCraft.instance.statConfig.mysql.username
            config.password = StatCraft.instance.statConfig.mysql.password
            config.addDataSourceProperty("databaseName", StatCraft.instance.statConfig.mysql.database)
            config.addDataSourceProperty("portNumber", StatCraft.instance.statConfig.mysql.port)
            config.addDataSourceProperty("serverName", StatCraft.instance.statConfig.mysql.hostname)

            config.addDataSourceProperty("cachePrepStmts", true)
            config.addDataSourceProperty("prepStmtCacheSize", 250)
            config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048)
            config.addDataSourceProperty("useServerPrepStmts", true)
            config.addDataSourceProperty("cacheCallableStmts", true)
            config.addDataSourceProperty("cacheResultSetMetadata", true)
            config.addDataSourceProperty("cacheServerConfiguration", true)
            config.addDataSourceProperty("useLocalSessionState", true)
            config.addDataSourceProperty("elideSetAutoCommits", true)
            config.addDataSourceProperty("alwaysSendSetIsolation", false)

            config.connectionTestQuery = "SELECT 1"
            config.isInitializationFailFast = true
            config.minimumIdle = 3
            config.maximumPoolSize  = 4

            dataSource = HikariDataSource(config)
            dataSource.transactionIsolation = "TRANSACTION_READ_COMMITTED"
        } catch (e: Exception) {
            e.printStackTrace()

            StatCraft.instance.error(" *** StatCraft was unable to communicate with the database,")
            StatCraft.instance.error(" *** please check your settings and reload, StatCraft will")
            StatCraft.instance.error(" *** now be disabled.")

            StatCraft.instance.disablePlugin()
        }
    }

    abstract fun escapeSql(s: String): String

    override fun setupDatabase() {
        for (table in Table.values()) {
            checkTable(table)
            if (!StatCraft.instance.isEnabled()) {
                break
            }
        }
        if (StatCraft.instance.isEnabled()) {
            StatCraft.instance.info("Database verified successfully.")
        }
    }

    private fun checkTable(table: Table) {
        var intPst: PreparedStatement? = null
        var resultSet: ResultSet? = null
        var intResultSet: ResultSet? = null

        try {
            connection.use { conn ->
                conn.prepareStatement("# noinspection SqlResolve\nSELECT ENGINE FROM information_schema.TABLES WHERE TABLE_NAME = ?")
                    .use { statement ->

                    statement.setString(1, table.getName())
                    try {
                        // This can fail because there is no table
                        resultSet = statement.executeQuery()
                    } catch (e: SQLException) {
                        remakeTable(table, false)
                    }

                    if (resultSet == null ) {
                        remakeTable(table, false)
                    }

                    val dbm = conn.metaData
                    val tables = dbm.getTables(null, null, table.getName(), null)

                    tables.use {
                        if (it.next() && resultSet!!.next()) {
                            // Table exists
                            // Make sure the engine is correct
                            if (resultSet!!.getString("ENGINE") != "InnoDB") {
                                StatCraft.instance.warn("${table.getName()} is using an incorrect engine.")
                                remakeTable(table, true)
                            } else {
                                // Make sure the columns are correct
                                intPst = conn.prepareStatement("SHOW COLUMNS FROM ${escapeSql(table.getName())}")
                                intResultSet = intPst?.executeQuery()

                                // Make sure there are columns
                                if (intResultSet?.last() == true) {
                                    // Make sure there is the correct number of columns
                                    if (intResultSet?.row != table.getColumnCount()) {
                                        StatCraft.instance.warn("${table.getName()} has an incorrect number of columns.")
                                        remakeTable(table, true)
                                    } else {
                                        // Make sure the columns are correct
                                        intResultSet?.beforeFirst()
                                        for (column in table.columnNames) {
                                            intResultSet?.next()
                                            if (intResultSet?.getString("Field") != column) {
                                                StatCraft.instance.warn("${table.getName()} has incorrect columns.")
                                                remakeTable(table, true)
                                                break
                                            }
                                        }
                                    }
                                } else {
                                    StatCraft.instance.warn("${table.getName()} has no columns.")
                                    remakeTable(table, true)
                                }
                            }
                        } else {
                            // Table does not exist
                            remakeTable(table, false)
                        }
                    }
                }
            }
        } finally {
            try { resultSet?.close() } catch (e: Exception) { e.printStackTrace() }
            try { intPst?.close() } catch (e: Exception) { e.printStackTrace() }
            try { intResultSet?.close() } catch (e: Exception) { e.printStackTrace() }
        }
    }

    private fun remakeTable(table: Table, ask: Boolean) {
        if (!ask || StatCraft.instance.statConfig.mysql.forceSetup) {
            dropTable(table)
            createTable(table)
            StatCraft.instance.info("Created table `${table.getName()}`")
        } else {
            StatCraft.instance.error(" *** ${table.getName()} is not setup correctly and conflicts with StatCraft's setup.")
            StatCraft.instance.error(" *** Change mysql.forceSetup, remove or rename this table, or create a new database for")
            StatCraft.instance.error(" *** StatCraft to use. StatCraft will not run unless there are no conflicting tables.")
            StatCraft.instance.error(" *** StatCraft will now be disabled.")
            StatCraft.instance.disablePlugin()
        }
    }

    private fun dropTable(table: Table) {
        connection.use {
            it.prepareStatement("DROP TABLE IF EXISTS ${escapeSql(table.getName())}").use {
                it.executeUpdate()
            }
        }
    }

    private fun createTable(table: Table) {
        connection.use {
            it.prepareStatement(table.create).use {
                it.executeUpdate()
            }
        }
    }

    override fun close() {
        dataSource.close()
    }

    override fun query(@Language("MySQL") query: String): DbStatement {
        return DbStatement(connection).query(query)
    }

    override fun execute(query: String, vararg params: Any) {
        var statement: DbStatement? = null
        try {
            statement = query(query).execute(params)
        } finally {
            statement?.close()
        }
    }

    override fun getFirstRow(@Language("MySQL") query: String, vararg params: Any): DbRow? {
        var statement: DbStatement? = null
        try {
            statement = query(query).execute(params)
            return statement.nextRow
        } finally {
            statement?.close()
        }
    }

    override fun <T> getFirstColumn(@Language("MySQL") query: String, vararg params: Any): T? {
        var statement: DbStatement? = null
        try {
            statement = query(query).execute(params)
            return statement.getFirstColumn()
        } finally {
            statement?.close()
        }
    }

    override fun <T> getFirstColumnResults(@Language("MySQL") query: String, vararg params: Any): List<T>? {
        val dbRows = ArrayList<T>()
        var result: T?
        query(query).execute(params).use {
            result = it.getFirstColumn()
            while (result != null) {
                dbRows.add(result as T)
                result = it.getFirstColumn()
            }
        }
        return dbRows
    }

    override fun getResults(@Language("MySQL") query: String, vararg params: Any): List<DbRow>? {
        var statement: DbStatement? = null
        try {
            statement = query(query).execute(params)
            return statement.results
        } finally {
            statement?.close()
        }
    }

    override fun executeUpdate(@Language("MySQL") query: String, vararg params: Any): Int {
        var statement: DbStatement? = null
        try {
            statement = query(query)
            return statement.executeUpdate(params)
        } finally {
            statement?.close()
        }
    }

    override fun getPlayerId(uuid: UUID) =
        synchronized(this) {
            if (playerMap.contains(uuid)) {
                return@synchronized playerMap[uuid]!!
            }

            // We don't have this UUID cached, so check the database
            var result: Int? = null
            // byte array representation of the UUID
            val array = uuid.toByte()
            try {
                result = getFirstColumn("SELECT p.id FROM players p WHERE p.uuid = ?", array)

                if (result == null) {
                    // This uuid isn't in the database yet, so add it
                    val name = StatCraft.instance.getPlayerName(uuid)

                    // Only add it to the database if we can actually get a player name
                    if (name != null) {
                        executeUpdate("INSERT INTO players (uuid, name) VALUES (?, ?)", array, name)

                        result = getFirstColumn("SELECT p.id FROM players p WHERE p.uuid = ?", array)
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            if (result != null) {
                playerMap[uuid] = result
            }

            return@synchronized result
        }

    override fun getPlayerId(name: String): Int? {
        if (StatCraft.instance.players.contains(name)) {
            // Get the UUID of the player were are looking for and use that instead
            return getPlayerId(StatCraft.instance.players[name] as UUID)
        }

        try {
            return getFirstColumn("SELECT p.id FROM players p WHERE p.name = ?", name)
        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        }
    }

    override fun getWorldId(uuid: UUID) =
        synchronized(this) {
            if (worldMap.contains(uuid)) {
                return@synchronized worldMap[uuid]!!
            }

            // We don't have the UUID cached, so check the database
            var result: Int? = null
            // byte array representation of the UUID
            val array = uuid.toByte()
            try {
                result = getFirstColumn("SELECT w.id FROM worlds w WHERE w.uuid = ?", array)

                if (result == null) {
                    // This uuid isn't in the database yet, so add it
                    val name = StatCraft.instance.getWorldName(uuid)

                    // Only add it to the database if we can actually get a world name
                    if (name != null) {
                        executeUpdate("INSERT INTO worlds (uuid, name, custom_name) VALUES (?, ?, ?)", array, name, name)

                        result = getFirstColumn("SELECT w.id FROM worlds w WHERE w.uuid = ?", array)
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            if (result != null) {
                playerMap[uuid] = result
            }

            return@synchronized result
        }

    override fun getWorldId(name: String): Int? {
        try {
            return getFirstColumn("SELECT w.id FROM worlds w WHERE w.name = ? OR w.custom_name = ?", name)
        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        }
    }

    override fun getPluginId(uuid: UUID) =
        synchronized(this) {
            if (pluginMap.contains(uuid)) {
                return@synchronized pluginMap[uuid]!!
            }

            // We don't have the UUID cached, so check the database
            var result: Int? = null
            // byte array representation of hte UUID
            val array = uuid.toByte()

            result = getFirstColumn("SELECT n.id FROM namespace n WHERE n.uuid = ?", array)

            if (result == null) {
                // This uuid isn't in the database yet, so add it
                executeUpdate("INSERT INTO namespace (uuid) VALUES (?)", array)

                result = getFirstColumn("SELECT n.id FROM namespace n WHERE n.uuid = ?", array)
            }

            if (result != null) {
                pluginMap[uuid] = result
            }

            return@synchronized result!!
        }

    override val connection: Connection
        get() = dataSource.connection
}
