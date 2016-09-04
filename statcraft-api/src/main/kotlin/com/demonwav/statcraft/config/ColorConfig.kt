/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.config

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable

@ConfigSerializable
data class ColorConfig(

    @field:Setting(comment = "The color of the @<playername> text that proceeds a public command response, which denotes\n" +
        "who ran the command.")
    var publicIdentifier: String = "GRAY",

    @field:Setting(comment = "Color of a player's name")
    var playerName: String = "RED",

    @field:Setting(comment = "The color of the stat title, which looks like:\n" +
        "- <stat title> -")
    var statTitle: String = "WHITE",

    @field:Setting(comment = "The label for the more specific stat, or just \"Total\"")
    var statLabel: String = "AQUA",

    @field:Setting(comment = "The value of the individual statistic")
    var statValue: String = "DARK_AQUA",

    @field:Setting(comment = "The vertical line that separates individual stats in some commands.")
    var statSeparator: String = "BLUE",

    @field:Setting(comment = "The number that proceeds the listing for -top# commands.")
    var listNumber: String = "WHITE"
)
