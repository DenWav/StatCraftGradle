/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sponge

import com.demonwav.statcraft.stats.Movement
import org.spongepowered.api.statistic.Statistic
import org.spongepowered.api.statistic.Statistics

enum class MoveMapper(val move: Movement.MovementStatType, val stat: Statistic?) {
    WALKING(Movement.MovementStatType.WALKING, Statistics.WALK_DISTANCE),
    CROUCHING(Movement.MovementStatType.CROUCHING, Statistics.CROUCH_DISTANCE),
    SPRINTING(Movement.MovementStatType.SPRINTING, Statistics.SPRINT_DISTANCE),
    SWIMMING(Movement.MovementStatType.SWIMMING, Statistics.SWIM_DISTANCE),
    FALLING(Movement.MovementStatType.FALLING, Statistics.FALL_DISTANCE),
    CLIMBING(Movement.MovementStatType.CLIMBING, Statistics.CLIMB_DISTANCE),
    FLYING(Movement.MovementStatType.FLYING, Statistics.FLY_DISTANCE),
    DIVING(Movement.MovementStatType.DIVING, Statistics.DIVE_DISTANCE),
    MINECART(Movement.MovementStatType.MINECART, Statistics.MINECART_DISTANCE),
    BOAT(Movement.MovementStatType.BOAT, Statistics.BOAT_DISTANCE),
    PIG(Movement.MovementStatType.PIG, Statistics.PIG_DISTANCE),
    HORSE(Movement.MovementStatType.HORSE, Statistics.HORSE_DISTANCE),
    ELYTRA(Movement.MovementStatType.ELYTRA, null);
}
