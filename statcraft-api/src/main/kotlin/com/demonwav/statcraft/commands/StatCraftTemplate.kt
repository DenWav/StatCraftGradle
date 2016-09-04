/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import com.demonwav.statcraft.StatCraft
import java.sql.Connection

/**
 * TODO - this will very likely change, I haven't looked at Sponge to see what will carry over
 */
abstract class StatCraftTemplate(protected val plugin: StatCraft) {
    /**
     * TODO
     */
    abstract fun hasPermission(sender: Any, args: Array<out String>?): Boolean

    /**
     * TODO
     */
    abstract fun playerStatResponse(name: String, args: List<String>, connection: Connection): String?

    /**
     * TODO
     */
    abstract fun serverStatResponse(num: Long, args: List<String>, connection: Connection): String?
}
