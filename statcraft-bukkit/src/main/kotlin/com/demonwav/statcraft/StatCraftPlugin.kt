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
        StatCraft.getInstance().preInit()
    }

    override fun onEnable() {
        StatCraft.getInstance().postInit()
    }

    override fun onDisable() {
        StatCraft.getInstance().shutdown()
    }
}
