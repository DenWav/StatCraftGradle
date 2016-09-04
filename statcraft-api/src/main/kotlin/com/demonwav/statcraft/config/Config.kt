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
data class Config(

    @field:Setting(comment = "MySQL Settings")
    var mysql: SqlConfig = SqlConfig(),

    @field:Setting(comment = "Statistics Settings")
    var stats: StatsConfig = StatsConfig(),

    @field:Setting(comment = "You can specify a server timezone for the last seen command, or just leave it as \"auto\".\n" +
        "If you specify a timezone, it must be a proper three letter abbreviation.")
    var timezone: String = "auto",

    @field:Setting(comment = "Change the look of the plugin's command responses by modifying these values.\n" +
        "For any of the color fields, this is the list of responses you can use:\n" +
        "\n" +
        " - BLACK\n" +
        " - DARK_BLUE\n" +
        " - DARK_GREEN\n" +
        " - DARK_AQUA\n" +
        " - DARK_RED\n" +
        " - DARK_PURPLE\n" +
        " - GOLD\n" +
        " - GRAY\n" +
        " - DARK_GRAY\n" +
        " - BLUE\n" +
        " - GREEN\n" +
        " - AQUA\n" +
        " - RED\n" +
        " - LIGHT_PURPLE\n" +
        " - YELLOW\n" +
        " - WHITE")
    var colors: ColorConfig = ColorConfig()
)
