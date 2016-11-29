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
 * Defines a statistic tracked by StatCraft.
 */
abstract class StatCraftStatistic {

    /**
     * The StatCraftApi instance which is associated with this StatCraftStatistic. This is assigned
     * when the statistic is registered with the API. Never null.
     */
    lateinit var api: StatCraftApi

    /**
     * The human readable name of this statistic. Not null.
     */
    abstract val name: String
    /**
     * The human readable description of this statistic. Optional. Not null.
     */
    open val description: String = ""
    /**
     * The StatCraftStatisticTypes which correspond with this StatCraftStatistic. Optional. Never null.
     */
    open val primaryStatTypes: Array<StatCraftStatisticType> = StatCraftApi.UNIT_TYPE_ARRAY
    /**
     * The StatCraftStatisticTypes which correspond to as secondary types for this StatCraftStatistic. Optional. Never null.
     */
    open val secondaryStatTypes: Array<StatCraftStatisticType> = StatCraftApi.UNIT_TYPE_ARRAY
    /**
     * The unique database index for this statistic.
     */
    abstract val index: Int

    /**
     * Build a database query off of this statistic, to retrieve or update information.
     */
    fun query(): StatCraftQuery {
        return StatCraftQuery(this)
    }
}
