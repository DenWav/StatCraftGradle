/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.listeners

import com.demonwav.statcraft.stats.Blocks
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.block.ChangeBlockEvent
import org.spongepowered.api.event.filter.Getter
import org.spongepowered.api.event.filter.cause.Root
import org.spongepowered.api.event.filter.type.Include
import org.spongepowered.api.world.World

class BlockListener {

    @Listener
    @Include(ChangeBlockEvent.Break::class, ChangeBlockEvent.Place::class)
    fun onBlockBreak(event: ChangeBlockEvent, @Root player: Player, @Getter("getTargetWorld") world: World) {

        val statType = if (event is ChangeBlockEvent.Break) {
            Blocks.BlockStatType.BROKEN
        } else {
            Blocks.BlockStatType.PLACED
        }

        for (transaction in event.transactions) {
            Blocks.getInstance().query().primaryType(statType)
                .incrementValue(player.uniqueId, world.uniqueId, transaction.original.state.type.name)
        }
    }
}
