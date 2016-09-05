/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

import java.util.*

class SpongeBaseCommand : AbstractBaseCommand() {
    override val subCommands: TreeMap<String, StatCraftTemplate>
        get() = throw UnsupportedOperationException()

    override fun registerCommand(cmd: String, command: StatCraftTemplate) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}