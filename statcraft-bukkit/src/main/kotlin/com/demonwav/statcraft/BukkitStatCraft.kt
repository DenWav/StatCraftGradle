/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import co.aikar.taskchain.BukkitTaskChainFactory
import com.demonwav.statcraft.api.StatCraftNamespace
import com.demonwav.statcraft.commands.BukkitBaseCommand
import com.demonwav.statcraft.listeners.AnimalsBredListener
import com.demonwav.statcraft.listeners.BlockListener
import com.demonwav.statcraft.listeners.BucketsListener
import com.demonwav.statcraft.listeners.DamageListener
import com.demonwav.statcraft.listeners.DeathsListener
import com.demonwav.statcraft.listeners.EatingListener
import com.demonwav.statcraft.listeners.FiresStartedListener
import com.demonwav.statcraft.listeners.FishCaughtListener
import com.demonwav.statcraft.listeners.HighestLevelListener
import com.demonwav.statcraft.listeners.ItemsListener
import com.demonwav.statcraft.listeners.JoinsListener
import com.demonwav.statcraft.listeners.JumpsListener
import com.demonwav.statcraft.listeners.KicksListener
import com.demonwav.statcraft.listeners.KillsListener
import com.demonwav.statcraft.listeners.MessagesSpokenListener
import com.demonwav.statcraft.listeners.MovementListener
import com.demonwav.statcraft.listeners.OnFireListener
import com.demonwav.statcraft.listeners.PlayTimeListener
import com.demonwav.statcraft.listeners.ProjectilesListener
import com.demonwav.statcraft.listeners.SeenListener
import com.demonwav.statcraft.listeners.SheepShearedListener
import com.demonwav.statcraft.listeners.SleepTimeListener
import com.demonwav.statcraft.listeners.TabCompletionsListener
import com.demonwav.statcraft.listeners.TntDetonatedListener
import com.demonwav.statcraft.listeners.ToolsBrokenListener
import com.demonwav.statcraft.listeners.WordsSpokenListener
import com.demonwav.statcraft.listeners.WorldChangesListener
import com.demonwav.statcraft.listeners.XpGainedListener
import com.demonwav.statcraft.sql.BukkitDatabaseManager
import com.demonwav.statcraft.sql.BukkitThreadManager
import java.io.File
import java.nio.file.Path
import java.util.UUID

@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class BukkitStatCraft(val plugin: StatCraftPlugin) : AbstractStatCraft() {

    private val databaseManager = BukkitDatabaseManager()
    private val threadManager = BukkitThreadManager()
    private val taskChainFactory = BukkitTaskChainFactory.create(plugin)!!
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
        plugin.server.pluginManager.apply {
            registerEvents(AnimalsBredListener(), plugin)
            registerEvents(BlockListener(), plugin)
            registerEvents(BucketsListener(), plugin)
            registerEvents(DamageListener(), plugin)
            registerEvents(DeathsListener(), plugin)
            registerEvents(EatingListener(), plugin)
            registerEvents(FiresStartedListener(), plugin)
            registerEvents(FishCaughtListener(), plugin)
            registerEvents(HighestLevelListener(), plugin)
            registerEvents(ItemsListener(), plugin)
            registerEvents(JoinsListener(), plugin)
            registerEvents(JumpsListener(), plugin)
            registerEvents(KicksListener(), plugin)
            registerEvents(KillsListener(), plugin)
            registerEvents(MessagesSpokenListener(), plugin)
            registerEvents(MovementListener(), plugin)
            registerEvents(OnFireListener(), plugin)
            registerEvents(PlayTimeListener(), plugin)
            registerEvents(ProjectilesListener(), plugin)
            registerEvents(SeenListener(), plugin)
            registerEvents(SheepShearedListener(), plugin)
            registerEvents(SleepTimeListener(), plugin)
            registerEvents(TabCompletionsListener(), plugin)
            registerEvents(TntDetonatedListener(), plugin)
            registerEvents(ToolsBrokenListener(), plugin)
            registerEvents(WordsSpokenListener(), plugin)
            registerEvents(WorldChangesListener(), plugin)
            registerEvents(XpGainedListener(), plugin)
        }
    }

    override fun createCommands() {
        plugin.getCommand("sc").executor = baseCommand
    }

    override fun getConfigFile() = configFile

    override fun getDatabaseManager() = databaseManager
    override fun getThreadManager() = threadManager
    override fun getTaskChain() = taskChainFactory
    override fun getBaseCommand() = baseCommand
    override fun getMoveUpdater() = moveUpdater

    override fun getPlayerName(uuid: UUID): String? = plugin.server.getPlayer(uuid).name
    override fun getWorldName(uuid: UUID): String? = plugin.server.getWorld(uuid).name

    override fun isEnabled() = plugin.isEnabled

    override fun disablePlugin() {
        shutdown()

        plugin.server.pluginManager.disablePlugin(plugin)
    }

    override fun info(s: String) = plugin.logger.info(s)
    override fun warn(s: String) = plugin.logger.warning(s)
    override fun error(s: String) = plugin.logger.severe(s)

    companion object {
        @JvmStatic
        lateinit var instance: BukkitStatCraft
    }
}
