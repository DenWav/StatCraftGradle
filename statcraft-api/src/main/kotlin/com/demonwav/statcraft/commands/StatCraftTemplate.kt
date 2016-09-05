/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import java.sql.Connection

/**
 * TODO - this will very likely change, I haven't looked at Sponge to see what will carry over
 */
interface StatCraftTemplate {
    /**
     * TODO
     */
    fun hasPermission(sender: Any, args: Array<out String>?): Boolean

    /**
     * TODO
     */
    fun playerStatResponse(name: String, args: List<String>, connection: Connection): String?

    /**
     * TODO
     */
    fun serverStatResponse(num: Long, args: List<String>, connection: Connection): String?
}
