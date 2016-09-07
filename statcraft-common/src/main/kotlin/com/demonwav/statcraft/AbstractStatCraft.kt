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
import com.demonwav.statcraft.api.exceptions.StatCraftNamespaceAlreadyDefinedException
import com.demonwav.statcraft.config.Config
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
import java.util.Calendar
import java.util.Collections
import java.util.HashMap
import java.util.TimeZone
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class AbstractStatCraft : StatCraft {

    // Config
    private lateinit var statConfig: Config

    private var timeZone: String = Calendar.getInstance().timeZone.getDisplayName(false, TimeZone.SHORT)

    private val players = ConcurrentHashMap<String, UUID>()

    // API Map
    protected val apiMap = HashMap<Any, StatCraftApi>()

    // StatCraft's API instance
    protected lateinit var api: StatCraftApi

    fun preInit() {
        val loader = HoconConfigurationLoader.builder().setPath(getConfigFile()).build()
        val configMapper = ObjectMapper.forClass(Config::class.java).bindToNew()

        // Load config
        val root = loader.load()
        statConfig = configMapper.populate(root)

        // Save config
        if (!configFile.parent.toFile().exists()) {
            configFile.parent.toFile().mkdir()
        }
        configMapper.serialize(root)
        loader.save(root)

        // Init database
        databaseManager.initialize()

        // Register this plugin with the StatCraft API, done here, so we are always first
        api = getApi(this)

        registerStats()
    }

    fun postInit() {
        databaseManager.setupDatabase()
        if (!isEnabled) {
            return
        }

        if (!statConfig.timezone.equals("auto", true)) {
            timeZone = statConfig.timezone
        }

        threadManager.startExecutors()

        createListeners()
        createCommands()
    }

    fun shutdown() {
        threadManager.close()
        threadManager.shutdown()
    }

    protected fun registerStats() {
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

    abstract fun createListeners()
    abstract fun createCommands()

    override fun getStatConfig(): Config = statConfig
    override fun setStatConfig(config: Config) {
        statConfig = config
    }

    override fun getTimeZone() = timeZone

    override fun getPlayers(): Map<String, UUID> = Collections.unmodifiableMap(players)!!

    override fun getApi(plugin: Any): StatCraftApi {
        var api = apiMap[plugin]
        if (api != null) {
            return api
        }

        api = StatCraftApi(plugin)

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
        lateinit var instance: AbstractStatCraft
    }
}
