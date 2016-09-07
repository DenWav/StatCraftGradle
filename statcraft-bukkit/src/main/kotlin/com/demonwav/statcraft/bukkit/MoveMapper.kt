/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.bukkit

import com.demonwav.statcraft.stats.Movement
import org.bukkit.Statistic

enum class MoveMapper(val move: Movement.MovementStatType, val stat: Statistic) {
    WALKING(Movement.MovementStatType.WALKING, Statistic.WALK_ONE_CM),
    CROUCHING(Movement.MovementStatType.CROUCHING, Statistic.CROUCH_ONE_CM),
    SPRINTING(Movement.MovementStatType.SPRINTING, Statistic.SPRINT_ONE_CM),
    SWIMMING(Movement.MovementStatType.SWIMMING, Statistic.SWIM_ONE_CM),
    FALLING(Movement.MovementStatType.FALLING, Statistic.FALL_ONE_CM),
    CLIMBING(Movement.MovementStatType.CLIMBING, Statistic.CLIMB_ONE_CM),
    FLYING(Movement.MovementStatType.FLYING, Statistic.FLY_ONE_CM),
    DIVING(Movement.MovementStatType.DIVING, Statistic.DIVE_ONE_CM),
    MINECART(Movement.MovementStatType.MINECART, Statistic.MINECART_ONE_CM),
    BOAT(Movement.MovementStatType.BOAT, Statistic.BOAT_ONE_CM),
    PIG(Movement.MovementStatType.PIG, Statistic.PIG_ONE_CM),
    HORSE(Movement.MovementStatType.HORSE, Statistic.HORSE_ONE_CM),
    ELYTRA(Movement.MovementStatType.ELYTRA, Statistic.AVIATE_ONE_CM);
}
