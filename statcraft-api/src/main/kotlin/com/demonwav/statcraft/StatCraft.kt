/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import com.demonwav.statcraft.api.StatCraftApi
import com.demonwav.statcraft.commands.BaseCommand
import com.demonwav.statcraft.config.Config
import com.demonwav.statcraft.sql.DatabaseManager
import com.demonwav.statcraft.sql.ThreadManager
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * TODO
 */
interface StatCraft {

    /**
     * TODO
     */
    val databaseManager: DatabaseManager

    /**
     * TODO
     */
    val lastFireTime: ConcurrentHashMap<UUID, Int>
    /**
     * TODO
     */
    val lastDrownTime: ConcurrentHashMap<UUID, Int>
    /**
     * TODO
     */
    val lastPoisonTime: ConcurrentHashMap<UUID, Int>
    /**
     * TODO
     */
    val lastWitherTime: ConcurrentHashMap<UUID, Int>

    /**
     * TODO
     */
    val threadManager: ThreadManager

    /**
     * TODO
     */
    val baseCommand: BaseCommand

    /**
     * TODO
     */
    var timeZone: String

    /**
     * TODO
     */
    val players: ConcurrentHashMap<String, UUID>
    /**
     * TODO
     */
    val moveUpdater: ServerStatUpdater.Move<*, *>

    /**
     * TODO
     */
    fun getStatConfig(): Config

    /**
     * TODO
     */
    fun getPlayerName(uuid: UUID): String?
    /**
     * TODO
     */
    fun getWorldName(uuid: UUID): String?

    /**
     * TODO
     */
    fun disablePlugin()
    /**
     * TODO
     */
    fun isEnabled(): Boolean

    /**
     * TODO
     */
    fun info(s: String)
    /**
     * TODO
     */
    fun warn(s: String)
    /**
     * TODO
     */
    fun error(s: String)

    fun getApi(plugin: Any): StatCraftApi

    companion object {
        lateinit var instance: StatCraft
    }
}
