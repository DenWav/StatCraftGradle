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
        Bukkit.getServer().scheduler.runTaskTimer(BukkitStatCraft.instance, main, 1L, 1L)
        Bukkit.getServer().scheduler.runTaskTimerAsynchronously(BukkitStatCraft.instance, async, 1L, 1L)
    }
}
