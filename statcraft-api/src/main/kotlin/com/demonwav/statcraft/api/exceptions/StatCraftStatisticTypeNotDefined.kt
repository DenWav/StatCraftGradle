/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api.exceptions

import com.demonwav.statcraft.api.StatCraftQuery
import com.demonwav.statcraft.api.StatCraftStatistic

/**
 * Thrown when a stat type is attempted to be set on a [StatCraftQuery] that the underlying [StatCraftStatistic] does not contain.
 */
class StatCraftStatisticTypeNotDefined : RuntimeException()
