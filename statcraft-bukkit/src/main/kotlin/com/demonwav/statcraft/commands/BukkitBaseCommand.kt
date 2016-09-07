/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import java.util.TreeMap

class BukkitBaseCommand : AbstractBaseCommand(), CommandExecutor, TabCompleter {
    override val subCommands: TreeMap<String, StatCraftTemplate>
        get() = throw UnsupportedOperationException()

    override fun registerCommand(cmd: String, command: StatCraftTemplate) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        TODO()
    }

    override fun onTabComplete(sender: CommandSender?, command: Command?, alias: String?, args: Array<out String>?): MutableList<String>? {
        TODO()
    }

}
