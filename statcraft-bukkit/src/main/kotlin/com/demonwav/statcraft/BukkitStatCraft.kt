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
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@StatCraftNamespace("4f89d232-eb02-47b8-abe0-fb42b617505b")
class BukkitStatCraft : JavaPlugin(), StatCraft {
    init {
        StatCraft.instance = this
        BukkitStatCraft.instance = this
    }

    override fun getPlayerName(uuid: UUID): String {
        return server.getPlayer(uuid).name
    }

    override fun getWorldName(uuid: UUID): String {
        return server.getWorld(uuid).name
    }

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
    override val moveUpdater: BukkitServerStatUpdater.BukkitMove
        get() = throw UnsupportedOperationException()
    override val statConfig: Config
        get() = throw UnsupportedOperationException()


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

    companion object {
        @JvmStatic
        lateinit var instance: BukkitStatCraft
    }
}
