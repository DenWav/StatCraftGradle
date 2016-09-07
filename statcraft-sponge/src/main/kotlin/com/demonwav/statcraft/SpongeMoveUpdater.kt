/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import org.spongepowered.api.Sponge
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.world.World

class SpongeMoveUpdater : MoveUpdater<Player, World> {

    override fun run() {
        Sponge.getServer().onlinePlayers.forEach { run(it) }
    }

    override fun run(player: Player) {
        run(player, player.world)
    }

    override fun run(player: Player, world: World) {
        TODO()
    }
}