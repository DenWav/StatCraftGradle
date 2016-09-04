/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import java.util.TreeMap

/**
 * TODO
 */
interface BaseCommand {

    /**
     * TODO
     */
    val subCommands: TreeMap<String, StatCraftTemplate>

    /**
     * TODO
     */
    fun registerCommand(cmd: String, command: StatCraftTemplate)
}
