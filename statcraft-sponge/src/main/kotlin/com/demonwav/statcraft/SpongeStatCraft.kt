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
import com.demonwav.statcraft.commands.SpongeBaseCommand
import com.demonwav.statcraft.listeners.BlockListener
import com.demonwav.statcraft.sql.SpongeDatabaseManager
import com.demonwav.statcraft.sql.SpongeThreadManager
import org.spongepowered.api.Sponge
import java.util.UUID

@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class SpongeStatCraft(val plugin: StatCraftPlugin) : AbstractStatCraft() {

    private val databaseManager = SpongeDatabaseManager()
    private val threadManager = SpongeThreadManager()
    private val baseCommand = SpongeBaseCommand()
    private val moveUpdater = SpongeMoveUpdater()

    init {
        // Set global states
        StatCraft.Companion.instance = this
        AbstractStatCraft.instance = this
        SpongeStatCraft.instance = this
    }

    override fun createListeners() {
        Sponge.getEventManager().registerListeners(plugin, BlockListener())
    }

    override fun getConfigFile() = plugin.defaultConfig

    override fun getDatabaseManager() = databaseManager
    override fun getThreadManager() = threadManager
    override fun getBaseCommand() = baseCommand
    override fun getMoveUpdater() = moveUpdater

    override fun getPlayerName(uuid: UUID) = Sponge.getServer().getPlayer(uuid).get()?.name
    override fun getWorldName(uuid: UUID) = Sponge.getServer().getWorld(uuid).get()?.name

    override fun isEnabled(): Boolean {
        // TODO
        return true
    }

    override fun disablePlugin() {
        // TODO
    }

    override fun info(s: String) = plugin.logger.info(s)
    override fun warn(s: String) = plugin.logger.warn(s)
    override fun error(s: String) = plugin.logger.error(s)

    companion object {
        @JvmStatic
        lateinit var instance: SpongeStatCraft
    }
}
