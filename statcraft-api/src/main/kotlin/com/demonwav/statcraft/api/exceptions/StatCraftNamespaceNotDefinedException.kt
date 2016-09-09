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
import com.demonwav.statcraft.StatCraft

/**
 * Thrown when [StatCraft.getApi] is called with an object that isn't annotated with [StatCraftNamespace].
 */
class StatCraftNamespaceNotDefinedException : RuntimeException()
