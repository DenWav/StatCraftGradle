/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api

import com.demonwav.statcraft.ExceptionalFuture
import com.demonwav.statcraft.StatCraft
import com.demonwav.statcraft.api.exceptions.StatCraftStatisticTypeNotDefined
import java.math.BigDecimal
import java.util.UUID
import java.util.concurrent.CompletableFuture

private const val EMPTY_STRING = ""
/**
 * The query builder which accepts primary and secondary types, and allows modifying or reading data for the statistic types.
 */
class StatCraftQuery(
    /**
     * The [StatCraftStatistic] which created this query object.
     */
    private val statistic: StatCraftStatistic
) {
    private var primaryType: StatCraftStatisticType = StatCraftApi.UNIT_TYPE
    private var secondaryType: StatCraftStatisticType = StatCraftApi.UNIT_TYPE

    fun primaryType(primaryType: StatCraftStatisticType): StatCraftQuery {
        if (!statistic.primaryStatTypes.contains(primaryType)) {
            throw StatCraftStatisticTypeNotDefined()
        }

        this.primaryType = primaryType
        return this
    }

    fun secondaryType(secondaryType: StatCraftStatisticType): StatCraftQuery {
        if (!statistic.secondaryStatTypes.contains(secondaryType)) {
            throw StatCraftStatisticTypeNotDefined()
        }

        this.secondaryType = secondaryType
        return this
    }

    // ***************************
    // Increment
    // ***************************
    /**
     * Add one to the value with no primary or secondary types.
     */
    fun incrementValue(player: UUID, world: UUID) {
        incrementValue(player, world, EMPTY_STRING)
    }

    /**
     * Add one to the value with no secondary type.
     */
    fun incrementValue(player: UUID, world: UUID, primaryTarget: String) {
        incrementValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * Add one to the value.
     */
    fun incrementValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String) {
        addValue(player, world, primaryTarget, secondaryTarget, 1)
    }

    // ***************************
    // Decrement
    // ***************************
    /**
     * Subtract one from the value with no primary or secondary types.
     */
    fun decrementValue(player: UUID, world: UUID) {
        decrementValue(player, world, EMPTY_STRING)
    }

    /**
     * Subtract one from the value with no secondary type.
     */
    fun decrementValue(player: UUID, world: UUID, primaryTarget: String) {
        decrementValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * Subtract one from the value.
     */
    fun decrementValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String) {
        subtractValue(player, world, primaryTarget, secondaryTarget, 1)
    }

    // ***************************
    // Add Value
    // ***************************
    /**
     * Add the given number to the value with no primary or secondary types.
     */
    fun addValue(player: UUID, world: UUID, value: Long) {
        addValue(player, world, EMPTY_STRING, value)
    }

    /**
     * Add the given number to the value with no secondary type.
     */
    fun addValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        addValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * Add the given number to the value.
     */
    fun addValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        StatCraft.getInstance().threadManager.scheduleUpdate(
            //language=MySQL
            """
            INSERT INTO stats (
              namespace_id,
              stat_id,
              player_id,
              world_id,
              primary_stat_type_id,
              secondary_stat_type_id,
              primary_stat_target,
              secondary_stat_target,
              `value`
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `value` = `value` + ?
            """.trimIndent(),

            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return,
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return,
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget,
            value,
            value
        )
    }

    // ***************************
    // Subtract Value
    // ***************************
    /**
     * Subtract the given number from the value with no primary or secondary types.
     */
    fun subtractValue(player: UUID, world: UUID, value: Long) {
        subtractValue(player, world, EMPTY_STRING, value)
    }

    /**
     * Subtract the given number from the value with no secondary type.
     */
    fun subtractValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        subtractValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * Subtract the given number from the value.
     */
    fun subtractValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        addValue(player, world, primaryTarget, secondaryTarget, -1 * value)
    }

    // ***************************
    // Multiply Value
    // ***************************
    /**
     * Multiply the value by the give number with no primary or secondary types.
     */
    fun multiplyValue(player: UUID, world: UUID, value: Long) {
        multiplyValue(player, world, EMPTY_STRING, value)
    }

    /**
     * Multiply the value by the given number with no secondary type.
     */
    fun multiplyValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        multiplyValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * Multiply the value by the given number.
     */
    fun multiplyValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        StatCraft.getInstance().threadManager.scheduleUpdate(
            //language=MySQL
            """
            UPDATE stats SET `value` = `value` * ? WHERE
            namespace_id = ? AND
            stat_id = ? AND
            player_id = ? AND
            world_id = ? AND
            primary_stat_type_id = ? AND
            secondary_stat_type_id = ? AND
            primary_stat_target = ? AND
            secondary_stat_target = ?
            """.trimIndent(),

            value,
            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return,
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return,
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget
        )
    }

    // ***************************
    // Divide Value
    // ***************************
    /**
     * Divide the value by the given number with no primary or secondary types.
     */
    fun divideValue(player: UUID, world: UUID, value: Long) {
        divideValue(player, world, EMPTY_STRING, value)
    }

    /**
     * Divide the value by the given number with no secondary type.
     */
    fun divideValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        divideValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * Divide the value by the given number.
     */
    fun divideValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        StatCraft.getInstance().threadManager.scheduleUpdate(
            //language=MySQL
            """
            UPDATE stats SET `value` = `value` / ? WHERE
            namespace_id = ? AND
            stat_id = ? AND
            player_id = ? AND
            world_id = ? AND
            primary_stat_type_id = ? AND
            secondary_stat_type_id = ? AND
            primary_stat_target = ? AND
            secondary_stat_target = ?
            """.trimIndent(),

            value,
            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return,
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return,
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget
        )
    }

    // ***************************
    // Set Value
    // ***************************
    /**
     * Set the value with no primary or secondary types.
     */
    fun setValue(player: UUID, world: UUID, value: Long) {
        setValue(player, world, EMPTY_STRING, value)
    }

    /**
     * Set the value with no secondary type.
     */
    fun setValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        setValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * Set the value.
     */
    fun setValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        StatCraft.getInstance().threadManager.scheduleUpdate(
            //language=MySQL
            """
            INSERT INTO stats (
              namespace_id,
              stat_id,
              player_id,
              world_id,
              primary_stat_type_id,
              secondary_stat_type_id,
              primary_stat_target,
              secondary_stat_target,
              `value`
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `value` = ?
            """.trimIndent(),

            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return,
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return,
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget,
            value,
            value
        )
    }

    // ***************************
    // Get Value
    // ***************************
    /**
     * Get a completable future for which when completed will hold the value with no primary or secondary types.
     */
    fun getValue(player: UUID, world: UUID): CompletableFuture<Long> {
        return getValue(player, world, EMPTY_STRING)
    }

    /**
     * Get a completable future for which when completed will hold the value with no secondary type.
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String): CompletableFuture<Long> {
        return getValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * Get a completable future for which when completed will hold the value.
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String): CompletableFuture<Long> {
        return StatCraft.getInstance().threadManager.scheduleQuery<Long>(
            //language=MySQL
            """
            SELECT s.value FROM stats s WHERE
            s.namespace_id = ? AND
            s.stat_id = ? AND
            s.player_id = ? AND
            s.world_id = ? AND
            s.primary_stat_type_id = ? AND
            s.secondary_stat_type_id = ? AND
            s.primary_stat_target = ? AND
            s.secondary_stat_target = ?
            """.trimIndent(),

            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return ExceptionalFuture.create(Exception("Failed to get player id")),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return ExceptionalFuture.create(Exception("Failed to get world id")),
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget
        )
    }

    /**
     * Get the sum of all values for this statistic for this player, ignoring both the primary and secondary types.
     */
    fun getSum(player: UUID, world: UUID): CompletableFuture<BigDecimal> {
        return StatCraft.getInstance().threadManager.scheduleQuery<BigDecimal>(
            //language=MySQL
            """
            SELECT SUM(s.value) FROM stats s WHERE
            s.namespace_id = ? AND
            s.stat_id = ? AND
            s.player_id = ? AND
            s.world_id = ? AND
            s.primary_stat_type_id = ? AND
            s.secondary_stat_type_id = ?
            """.trimIndent(),

            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return ExceptionalFuture.create(Exception("Failed to get player id")),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return ExceptionalFuture.create(Exception("Failed to get world id")),
            primaryType.index,
            secondaryType.index
        )
    }

    /**
     * Get the sum of all values for this statistic for this player, ignoring the secondary type.
     */
    fun getSum(player: UUID, world: UUID, primaryTarget: String): CompletableFuture<BigDecimal> {
        return StatCraft.getInstance().threadManager.scheduleQuery<BigDecimal>(
            //language=MySQL
            """
            SELECT SUM(s.value) FROM stats s WHERE
            s.namespace_id = ? AND
            s.stat_id = ? AND
            s.player_id = ? AND
            s.world_id = ? AND
            s.primary_stat_type_id = ? AND
            s.secondary_stat_type_id = ? AND
            s.primary_stat_target = ?
            """.trimIndent(),

            statistic.api.id,
            statistic.index,
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return ExceptionalFuture.create(Exception("Failed to get player id")),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return ExceptionalFuture.create(Exception("Failed to get world id")),
            primaryType.index,
            secondaryType.index,
            primaryTarget
        )
    }
}
