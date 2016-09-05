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
import com.demonwav.statcraft.commands.BaseCommand
import com.demonwav.statcraft.commands.SpongeBaseCommand
import com.demonwav.statcraft.config.Config
import com.demonwav.statcraft.sql.DatabaseManager
import com.demonwav.statcraft.sql.SpongeDatabaseManager
import com.demonwav.statcraft.sql.SpongeThreadManager
import com.demonwav.statcraft.sql.ThreadManager
import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Plugin(
    id = "statcraft",
    name = "StatCraft",
    version = "1.0-SNAPSHOT"
)
@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class SpongeStatCraft : StatCraft {

    @Inject
    private lateinit var logger: Logger

    private val databaseManager = SpongeDatabaseManager()

    private val lastFireTime = ConcurrentHashMap<UUID, Int>()
    private val lastDrownTime = ConcurrentHashMap<UUID, Int>()
    private val lastPoisonTime = ConcurrentHashMap<UUID, Int>()
    private val lastWitherTime = ConcurrentHashMap<UUID, Int>()

    private val threadManager = SpongeThreadManager()

    private val baseCommand = SpongeBaseCommand()

    private var timeZone: String = Calendar.getInstance().timeZone.getDisplayName(false, TimeZone.SHORT)

    private val players = ConcurrentHashMap<String, UUID>()
    private val moveUpdater = SpongeServerStatUpdater.SpongeMove()

    init {
        // Set global states
        StatCraft.Companion.instance = this
        SpongeStatCraft.instance = this
    }

    override fun getDatabaseManager() = databaseManager

    override fun getLastFireTime() = lastFireTime
    override fun getlastDrownTime() = lastDrownTime
    override fun getLastPoisonTime() = lastPoisonTime
    override fun getLastWitherTime() = lastWitherTime

    override fun getThreadManager() = threadManager

    override fun getBaseCommand() = baseCommand

    override fun getTimeZone() = timeZone

    override fun getPlayers() = players
    override fun getMoveUpdater() = moveUpdater

    override fun getStatConfig(): Config {
        TODO()
    }

    override fun getPlayerName(uuid: UUID): String? {
        return Sponge.getServer().getPlayer(uuid).get()?.name
    }

    override fun getWorldName(uuid: UUID): String? {
        return Sponge.getServer().getWorld(uuid).get()?.name
    }

    override fun isEnabled(): Boolean {
        TODO()
    }

    override fun disablePlugin() {
        // TODO
    }

    override fun info(s: String) {
        logger.info(s)
    }

    override fun warn(s: String) {
        logger.warn(s)
    }

    override fun error(s: String) {
        logger.error(s)
    }

    override fun getApi(plugin: Any): StatCraftApi {
        TODO()
    }

    companion object {
        @JvmStatic
        lateinit var instance: SpongeStatCraft
    }
}
