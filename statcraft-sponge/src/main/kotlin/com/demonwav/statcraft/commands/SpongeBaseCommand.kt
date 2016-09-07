/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.command.spec.CommandExecutor
import java.util.TreeMap

class SpongeBaseCommand : AbstractBaseCommand(), CommandExecutor {
    override val subCommands = TreeMap<String, StatCraftTemplate>()

    override fun execute(src: CommandSource?, args: CommandContext?): CommandResult {
        TODO()
    }

    override fun registerCommand(cmd: String, command: StatCraftTemplate) {
        TODO()
    }
}