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
import com.demonwav.statcraft.api.StatCraftNamespace
import com.demonwav.statcraft.api.exceptions.StatCraftNamespaceAlreadyDefinedException
import com.demonwav.statcraft.commands.BaseCommand
import com.demonwav.statcraft.config.Config
import com.demonwav.statcraft.listeners.BlockListener
import com.demonwav.statcraft.sql.BukkitDatabaseManager
import com.demonwav.statcraft.sql.BukkitThreadManager
import com.demonwav.statcraft.stats.AnimalsBred
import com.demonwav.statcraft.stats.Blocks
import com.demonwav.statcraft.stats.Buckets
import com.demonwav.statcraft.stats.Damage
import com.demonwav.statcraft.stats.Deaths
import com.demonwav.statcraft.stats.Eating
import com.demonwav.statcraft.stats.FiresStarted
import com.demonwav.statcraft.stats.FishCaught
import com.demonwav.statcraft.stats.HighestLevel
import com.demonwav.statcraft.stats.Items
import com.demonwav.statcraft.stats.Joins
import com.demonwav.statcraft.stats.Jumps
import com.demonwav.statcraft.stats.Kicks
import com.demonwav.statcraft.stats.Kills
import com.demonwav.statcraft.stats.MessagesSpoken
import com.demonwav.statcraft.stats.Movement
import com.demonwav.statcraft.stats.OnFire
import com.demonwav.statcraft.stats.PlayTime
import com.demonwav.statcraft.stats.Projectiles
import com.demonwav.statcraft.stats.Seen
import com.demonwav.statcraft.stats.SheepSheared
import com.demonwav.statcraft.stats.SleepTime
import com.demonwav.statcraft.stats.TabCompletions
import com.demonwav.statcraft.stats.TntDetonated
import com.demonwav.statcraft.stats.ToolsBroken
import com.demonwav.statcraft.stats.WordsSpoken
import com.demonwav.statcraft.stats.WorldChanges
import com.demonwav.statcraft.stats.XpGained
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import ninja.leaping.configurate.objectmapping.ObjectMapper
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.Calendar
import java.util.HashMap
import java.util.TimeZone
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class BukkitStatCraft : JavaPlugin(), StatCraft {

    override val databaseManager = BukkitDatabaseManager()

    override val lastFireTime = ConcurrentHashMap<UUID, Int>()
    override val lastDrownTime = ConcurrentHashMap<UUID, Int>()
    override val lastPoisonTime = ConcurrentHashMap<UUID, Int>()
    override val lastWitherTime = ConcurrentHashMap<UUID, Int>()

    override val threadManager = BukkitThreadManager()

    override val baseCommand: BaseCommand
        get() = throw UnsupportedOperationException()

    override var timeZone: String = Calendar.getInstance().timeZone.getDisplayName(false, TimeZone.SHORT)

    override val players = ConcurrentHashMap<String, UUID>()
    override val moveUpdater = BukkitServerStatUpdater.BukkitMove()

    // Config
    private var _statConfig: Config? = null

    // API Map
    private val apiMap = HashMap<Any, StatCraftApi>()

    // StatCraft's API instance
    lateinit var api: StatCraftApi

    init {
        // Set global states
        StatCraft.instance = this
        BukkitStatCraft.instance = this
    }

    override fun onLoad() {
        val loader = HoconConfigurationLoader.builder().setPath(File(dataFolder, "config.conf").toPath()).build()
        val configMapper = ObjectMapper.forClass(Config::class.java).bindToNew()

        // Load config
        val root = loader.load()
        _statConfig = configMapper.populate(root)

        // Save config
        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }
        configMapper.serialize(root)
        loader.save(root)

        // Init database
        databaseManager.initialize()

        // Register this plugin with the StatCraft API, done here, so we are always first
        api = getApi(this)

        registerStats()
    }

    override fun onEnable() {
        databaseManager.setupDatabase()
        if (!isEnabled) {
           return
        }

        if (!getStatConfig().timezone.equals("auto", true)) {
            timeZone = getStatConfig().timezone
        }

        threadManager.startExecutors()

        createListeners()
    }

    fun registerStats() {
        api.registerStatistic(AnimalsBred.getInstance())
        api.registerStatistic(Blocks.getInstance())
        api.registerStatistic(Buckets.getInstance())
        api.registerStatistic(Damage.getInstance())
        api.registerStatistic(Deaths.getInstance())
        api.registerStatistic(Eating.getInstance())
        api.registerStatistic(FiresStarted.getInstance())
        api.registerStatistic(FishCaught.getInstance())
        api.registerStatistic(HighestLevel.getInstance())
        api.registerStatistic(Items.getInstance())
        api.registerStatistic(Joins.getInstance())
        api.registerStatistic(Jumps.getInstance())
        api.registerStatistic(Kicks.getInstance())
        api.registerStatistic(Kills.getInstance())
        api.registerStatistic(MessagesSpoken.getInstance())
        api.registerStatistic(Movement.getInstance())
        api.registerStatistic(OnFire.getInstance())
        api.registerStatistic(PlayTime.getInstance())
        api.registerStatistic(Projectiles.getInstance())
        api.registerStatistic(Seen.getInstance())
        api.registerStatistic(SheepSheared.getInstance())
        api.registerStatistic(SleepTime.getInstance())
        api.registerStatistic(TabCompletions.getInstance())
        api.registerStatistic(TntDetonated.getInstance())
        api.registerStatistic(ToolsBroken.getInstance())
        api.registerStatistic(WordsSpoken.getInstance())
        api.registerStatistic(WorldChanges.getInstance())
        api.registerStatistic(XpGained.getInstance())
    }

    fun createListeners() {
        server.pluginManager.registerEvents(BlockListener(), this)
    }

    override fun getStatConfig() = _statConfig!!

    override fun getPlayerName(uuid: UUID): String {
        return server.getPlayer(uuid).name
    }

    override fun getWorldName(uuid: UUID): String {
        return server.getWorld(uuid).name
    }

    override fun disablePlugin() {
        server.pluginManager.disablePlugin(this)
    }

    override fun info(s: String) {
        logger.info(s)
    }

    override fun warn(s: String) {
        logger.warning(s)
    }

    override fun error(s: String) {
        logger.severe(s)
    }

    override fun getApi(plugin: Any): StatCraftApi {
        if (apiMap.containsKey(plugin)) {
            return apiMap[plugin]!!
        }

        val api = StatCraftApi(plugin)

        for ((key, value) in apiMap) {
            if (value.namespace == api.namespace) {
                throw StatCraftNamespaceAlreadyDefinedException()
            }
        }

        apiMap[plugin] = api

        return api
    }

    companion object {
        @JvmStatic
        lateinit var instance: BukkitStatCraft
    }
}
