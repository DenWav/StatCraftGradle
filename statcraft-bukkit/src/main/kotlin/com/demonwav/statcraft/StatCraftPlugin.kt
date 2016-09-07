/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import org.bukkit.plugin.java.JavaPlugin

class StatCraftPlugin : JavaPlugin() {

    init {
        BukkitStatCraft(this)
    }

    override fun onLoad() {
        BukkitStatCraft.instance.preInit()
    }

    override fun onEnable() {
        BukkitStatCraft.instance.postInit()
    }

    override fun onDisable() {
        BukkitStatCraft.instance.shutdown()
    }
}
