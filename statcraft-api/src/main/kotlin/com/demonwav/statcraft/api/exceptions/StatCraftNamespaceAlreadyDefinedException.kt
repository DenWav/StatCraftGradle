/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.api.exceptions

import com.demonwav.statcraft.api.StatCraftApi
import com.demonwav.statcraft.api.StatCraftNamespace
import com.demonwav.statcraft.StatCraft

/**
 * This exception is thrown when a [StatCraftApi] has already been created using the same [StatCraftNamespace].
 * @see StatCraft.getApi
 */
class StatCraftNamespaceAlreadyDefinedException : RuntimeException()
