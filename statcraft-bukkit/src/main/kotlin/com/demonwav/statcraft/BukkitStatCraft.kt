/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import com.demonwav.statcraft.api.StatCraftNamespace
import com.demonwav.statcraft.commands.BukkitBaseCommand
import com.demonwav.statcraft.listeners.BlockListener
import com.demonwav.statcraft.sql.BukkitDatabaseManager
import com.demonwav.statcraft.sql.BukkitThreadManager
import java.io.File
import java.nio.file.Path
import java.util.UUID

@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class BukkitStatCraft(val plugin: StatCraftPlugin) : AbstractStatCraft() {

    private val databaseManager = BukkitDatabaseManager()
    private val threadManager = BukkitThreadManager()
    private val baseCommand = BukkitBaseCommand()
    private val moveUpdater = BukkitMoveUpdater()

    private val configFile: Path

    init {
        // Set global states
        StatCraft.Companion.instance = this
        AbstractStatCraft.instance = this
        BukkitStatCraft.instance = this

        configFile = File(plugin.dataFolder, "statcraft.conf").toPath()
    }

    override fun createListeners() {
        plugin.server.pluginManager.registerEvents(BlockListener(), plugin)
    }

    override fun getConfigFile() = configFile

    override fun getDatabaseManager() = databaseManager
    override fun getThreadManager() = threadManager
    override fun getBaseCommand() = baseCommand
    override fun getMoveUpdater() = moveUpdater

    override fun getPlayerName(uuid: UUID): String? = plugin.server.getPlayer(uuid).name
    override fun getWorldName(uuid: UUID): String? = plugin.server.getWorld(uuid).name

    override fun isEnabled() = plugin.isEnabled

    override fun disablePlugin() = plugin.server.pluginManager.disablePlugin(plugin)

    override fun info(s: String) = plugin.logger.info(s)
    override fun warn(s: String) = plugin.logger.warning(s)
    override fun error(s: String) = plugin.logger.severe(s)

    companion object {
        @JvmStatic
        lateinit var instance: BukkitStatCraft
    }
}
