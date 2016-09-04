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
import com.demonwav.statcraft.api.exceptions.StatCraftNamespaceNotDefinedException
import java.util.ArrayList
import java.util.UUID

/**
 * TODO
 */
abstract class StatCraftApi<out T : Any>(val plugin: T, val statcraft: StatCraft) {

    private var namespaceString: String? = null
    /**
     * TODO
     */
    val namespace: UUID by lazy {
        UUID.fromString(namespaceString)
    }

    /**
     * TODO
     */
    val stats = ArrayList<StatCraftStatistic>()

    init {
        val clazz = plugin.javaClass
        val annotations = clazz.annotations
        for (annotation in annotations) {
            if (annotation is StatCraftNamespace) {
                namespaceString = annotation.value
                break
            }
        }

        if (namespaceString == null) {
            throw StatCraftNamespaceNotDefinedException()
        }
    }

    /**
     * TODO
     */
    fun registerStatistic(stat: StatCraftStatistic) {
        stat.api = this
        stats.add(stat)
    }

    companion object {
        /**
         * TODO
         */
        @JvmField
        val UNIT_TYPE = object : StatCraftStatisticType {
            override val name: String
                get() = "UNIT"
            override val index: Int
                get() = Int.MAX_VALUE
        }

        /**
         * TODO
         */
        @JvmField
        val UNIT_TYPE_ARRAY = arrayOf(UNIT_TYPE)
    }
}
