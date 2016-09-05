/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api

/**
 * TODO
 */
abstract class StatCraftStatistic {

    /**
     * The StatCraftApi instance which is associated with this StatCraftStatistic. This is assigned
     * when the statistic is registered with the API. Never null.
     */
    lateinit var api: StatCraftApi<*>

    /**
     * The human readable name of this statistic. Not null.
     */
    abstract val name: String
    /**
     * The human readable description of this statistic. Optional. Not null.
     */
    open val description: String = ""
    /**
     * The StatCraftStatisticTypes which correspond with this StatCraftStatistic. Never null.
     *
     * If the idea of stat types does not apply to this particular statistic, then simply return [StatCraftApi.UNIT_TYPE_ARRAY]
     */
    open val primaryStatTypes: Array<StatCraftStatisticType> = StatCraftApi.UNIT_TYPE_ARRAY
    /**
     * The StatCraftStatisticTypes which correspond to as secondary types for this StatCraftStatistic. Never null.
     *
     * If the idea of a secondary stat type does not apply to this particular statistic, then simply return [StatCraftApi.UNIT_TYPE_ARRAY]
     */
    open val secondaryStatTypes: Array<StatCraftStatisticType> = StatCraftApi.UNIT_TYPE_ARRAY
    /**
     * The unique database index for this statistic.
     */
    abstract val index: Int

    /**
     * TODO
     */
    fun query(): StatCraftQuery.StatCraftStatisticQueryBuilder {
        return StatCraftQuery.StatCraftStatisticQueryBuilder(this)
    }
}
