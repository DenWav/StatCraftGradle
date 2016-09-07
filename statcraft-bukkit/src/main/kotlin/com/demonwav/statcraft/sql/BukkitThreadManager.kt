/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.demonwav.statcraft.BukkitStatCraft
import org.bukkit.Bukkit

class BukkitThreadManager : AbstractThreadManager() {
    override fun startExecutors() {
        Bukkit.getServer().scheduler.runTaskTimer(BukkitStatCraft.instance.plugin, main, 1L, 1L)
        Bukkit.getServer().scheduler.runTaskTimerAsynchronously(BukkitStatCraft.instance.plugin, async, 1L, 1L)
    }

    override fun shutdown() {
        Bukkit.getServer().scheduler.cancelTasks(BukkitStatCraft.instance.plugin)
    }
}
