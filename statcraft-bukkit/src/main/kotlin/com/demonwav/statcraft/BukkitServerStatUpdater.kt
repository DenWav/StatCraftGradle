/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player

class BukkitServerStatUpdater : ServerStatUpdater {

    class BukkitMove : ServerStatUpdater.Move<Player, World> {
        override fun run() {
            Bukkit.getServer().onlinePlayers.forEach { run(it) }
        }

        override fun run(player: Player) {
            run(player, player.world)
        }

        override fun run(player: Player, world: World) {
            TODO()
        }

    }
}
