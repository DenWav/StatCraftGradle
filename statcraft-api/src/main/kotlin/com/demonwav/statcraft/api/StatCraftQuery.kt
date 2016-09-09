/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api

import com.demonwav.statcraft.Promise
import com.demonwav.statcraft.StatCraft
import com.demonwav.statcraft.api.exceptions.StatCraftStatisticTypeNotDefined
import java.math.BigDecimal
import java.util.UUID

/**
 * TODO
 */
class StatCraftQuery(
    /**
     * TODO
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
     * TODO
     */
    fun incrementValue(player: UUID, world: UUID) {
        incrementValue(player, world, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun incrementValue(player: UUID, world: UUID, primaryTarget: String) {
        incrementValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun incrementValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String) {
        addValue(player, world, primaryTarget, secondaryTarget, 1)
    }

    // ***************************
    // Decrement
    // ***************************
    /**
     * TODO
     */
    fun decrementValue(player: UUID, world: UUID) {
        decrementValue(player, world, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun decrementValue(player: UUID, world: UUID, primaryTarget: String) {
        decrementValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun decrementValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String) {
        subtractValue(player, world, primaryTarget, secondaryTarget, 1)
    }

    // ***************************
    // Add Value
    // ***************************
    /**
     * TODO
     */
    fun addValue(player: UUID, world: UUID, value: Long) {
        addValue(player, world, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun addValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        addValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * TODO
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
     * TODO
     */
    fun subtractValue(player: UUID, world: UUID, value: Long) {
        subtractValue(player, world, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun subtractValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        subtractValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun subtractValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String, value: Long) {
        addValue(player, world, primaryTarget, secondaryTarget, -1 * value)
    }

    // ***************************
    // Multiply Value
    // ***************************
    /**
     * TODO
     */
    fun multiplyValue(player: UUID, world: UUID, value: Long) {
        multiplyValue(player, world, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun multiplyValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        multiplyValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * TODO
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
     * TODO
     */
    fun divideValue(player: UUID, world: UUID, value: Long) {
        divideValue(player, world, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun divideValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        divideValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * TODO
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
     * TODO
     */
    fun setValue(player: UUID, world: UUID, value: Long) {
        setValue(player, world, EMPTY_STRING, value)
    }

    /**
     * TODO
     */
    fun setValue(player: UUID, world: UUID, primaryTarget: String, value: Long) {
        setValue(player, world, primaryTarget, EMPTY_STRING, value)
    }

    /**
     * TODO
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
     * TODO
     */
    fun getValue(player: UUID, world: UUID): Promise<Long> {
        return getValue(player, world, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String): Promise<Long> {
        return getValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String): Promise<Long> {
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
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return Promise.EMPTY_PROMISE(),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return Promise.EMPTY_PROMISE(),
            primaryType.index,
            secondaryType.index,
            primaryTarget,
            secondaryTarget
        )
    }

    /**
     * TODO
     */
    fun getSum(player: UUID, world: UUID): Promise<BigDecimal> {
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
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return Promise.EMPTY_PROMISE(),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return Promise.EMPTY_PROMISE(),
            primaryType.index,
            secondaryType.index
        )
    }

    /**
     * TODO
     */
    fun getSum(player: UUID, world: UUID, primaryTarget: String): Promise<BigDecimal> {
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
            StatCraft.getInstance().databaseManager.getPlayerId(player) ?: return Promise.EMPTY_PROMISE(),
            StatCraft.getInstance().databaseManager.getWorldId(world) ?: return Promise.EMPTY_PROMISE(),
            primaryType.index,
            secondaryType.index,
            primaryTarget
        )
    }

    companion object {
        @JvmStatic
        private val EMPTY_STRING = ""
    }
}
