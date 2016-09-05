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
import com.demonwav.statcraft.commands.BaseCommand
import com.demonwav.statcraft.config.Config
import com.demonwav.statcraft.sql.DatabaseManager
import com.demonwav.statcraft.sql.ThreadManager
import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.Sponge
import org.spongepowered.api.plugin.Plugin
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Plugin(
    id = "statcraft",
    name = "StatCraft",
    version = "1.0-SNAPSHOT"
)
@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class SpongeStatCraft : StatCraft {
    init {
        StatCraft.instance = this
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

    @Inject
    private lateinit var logger: Logger

    override val databaseManager: DatabaseManager
        get() = throw UnsupportedOperationException()
    override val lastFireTime: ConcurrentHashMap<UUID, Int>
        get() = throw UnsupportedOperationException()
    override val lastDrownTime: ConcurrentHashMap<UUID, Int>
        get() = throw UnsupportedOperationException()
    override val lastPoisonTime: ConcurrentHashMap<UUID, Int>
        get() = throw UnsupportedOperationException()
    override val lastWitherTime: ConcurrentHashMap<UUID, Int>
        get() = throw UnsupportedOperationException()
    override val threadManager: ThreadManager
        get() = throw UnsupportedOperationException()
    override val baseCommand: BaseCommand
        get() = throw UnsupportedOperationException()
    override var timeZone: String
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override val players: ConcurrentHashMap<String, UUID>
        get() = throw UnsupportedOperationException()
    override val moveUpdater: ServerStatUpdater.Move<*, *>
        get() = throw UnsupportedOperationException()
    override val statConfig: Config
        get() = throw UnsupportedOperationException()


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
}
