/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api.exceptions

import com.demonwav.statcraft.api.StatCraftNamespace

/**
 * Thrown when the database is unable to retrieve or create an integer ID for a [StatCraftNamespace]. This should generally never happen...
 * at least in a perfect world, anyways.
 */
class StatCraftNamespaceIdNullException : RuntimeException()
