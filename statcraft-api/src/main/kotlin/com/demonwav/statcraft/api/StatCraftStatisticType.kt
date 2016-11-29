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
 * Subtype for the [StatCraftStatistic].
 */
interface StatCraftStatisticType {

    /**
     * The public, human readable name for the statistic type. Not null.
     */
    val name: String

    /**
     * The `long` value which will be used to refer to this statistic in the database.
     * Since this will be the only way of connecting the stat value in the database to the
     * stat type, this value must not change for any given stat type.
     */
    val index: Int
}
