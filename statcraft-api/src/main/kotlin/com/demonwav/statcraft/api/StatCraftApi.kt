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
import com.demonwav.statcraft.api.exceptions.StatCraftNamespaceIdNullException
import com.demonwav.statcraft.api.exceptions.StatCraftNamespaceNotDefinedException
import java.util.ArrayList
import java.util.UUID

/**
 * Class for plugin-specific API's.
 */
class StatCraftApi(val plugin: Any) {

    private var namespaceString: String? = null
    /**
     * The namespace UUID for the plugin this [StatCraftApi] instance is connected to.
     */
    val namespace: UUID by lazy {
        UUID.fromString(namespaceString)
    }
    /**
     * The internal integer id for the plugin this [StatCraftApi] instance is connected to.
     */
    val id: Int by lazy {
        StatCraft.getInstance().databaseManager.getPluginId(namespace) ?:
                throw StatCraftNamespaceIdNullException()
    }

    /**
     * The statistics defined by this [StatCraftApi] instance.
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
     * Register a new [StatCraftStatistic] with the plugin this [StatCraftApi] is connected to.
     */
    fun registerStatistic(stat: StatCraftStatistic) {
        stat.api = this
        stats.add(stat)
    }

    companion object {
        /**
         * Considered the "empty" [StatCraftStatisticType].
         */
        @JvmField
        val UNIT_TYPE = object : StatCraftStatisticType {
            override val name: String
                get() = "UNIT"
            override val index: Int
                get() = Int.MAX_VALUE
        }

        /**
         * Considered an array of the "empty" [StatCraftStatisticType].
         */
        @JvmField
        val UNIT_TYPE_ARRAY = arrayOf(UNIT_TYPE)
    }
}
