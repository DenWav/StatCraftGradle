/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api

import com.demonwav.statcraft.StatCraft
import com.demonwav.statcraft.api.exceptions.StatCraftStatisticTypeNotDefined
import java.util.UUID

/**
 * TODO
 */
class StatCraftStatisticQuery(
    /**
     * TODO
     */
    private val plugin: StatCraft,
    /**
     * TODO
     */
    private val statistic: StatCraftStatistic,
    /**
     * TODO
     */
    private val primaryType: StatCraftStatisticType,
    /**
     * TODO
     */
    private val secondaryType: StatCraftStatisticType
) {
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
        TODO()
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
        TODO()
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
        TODO()
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
        TODO()
    }

    // ***************************
    // Get Value
    // ***************************
    /**
     * TODO
     */
    fun getValue(player: UUID, world: UUID): Long {
        return getValue(player, world, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String): Long {
        return getValue(player, world, primaryTarget, EMPTY_STRING)
    }

    /**
     * TODO
     */
    fun getValue(player: UUID, world: UUID, primaryTarget: String, secondaryTarget: String): Long {
        TODO()
    }

    /**
     * TODO
     */
    class StatCraftStatisticQueryBuilder(
        /**
         * TODO
         */
        private val plugin: StatCraft,
        /**
         * TODO
         */
        private val statistic: StatCraftStatistic
    ) {
        /**
         * TODO
         */
        private var primaryType: StatCraftStatisticType = StatCraftApi.UNIT_TYPE
        /**
         * TODO
         */
        private var secondaryType: StatCraftStatisticType = StatCraftApi.UNIT_TYPE

        /**
         * TODO
         */
        fun primaryType(type: StatCraftStatisticType): StatCraftStatisticQueryBuilder {
            primaryType = type
            return this
        }

        /**
         * TODO
         */
        fun secondaryType(type: StatCraftStatisticType): StatCraftStatisticQueryBuilder {
            secondaryType = type
            return this
        }

        /**
         * TODO
         */
        fun build(): StatCraftStatisticQuery {
            if (!statistic.primaryStatTypes.contains(primaryType)) {
                throw StatCraftStatisticTypeNotDefined()
            }

            if (!statistic.secondaryStatTypes.contains(secondaryType)) {
                throw StatCraftStatisticTypeNotDefined()
            }

            return StatCraftStatisticQuery(plugin, statistic, primaryType, secondaryType)
        }
    }

    companion object {
        @JvmStatic
        private val EMPTY_STRING = ""
    }
}
