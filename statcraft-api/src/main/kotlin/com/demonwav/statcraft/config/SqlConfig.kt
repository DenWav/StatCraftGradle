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
data class SqlConfig(
    @field:Setting
    var hostname: String = "localhost",
    @field:Setting
    var username: String = "statcraft",
    @field:Setting
    var password: String = "",
    @field:Setting
    var database: String = "statcraft",
    @field:Setting
    var port: String = "3306",

    @field:Setting(comment = "StatCraft will attempt to setup the database when it starts, but if there are already conflicting\n" +
        "tables that are improperly setup, it will only drop those tables if this settings is set to `true`.\n" +
        "StatCraft will not run unless all of the tables are setup correctly. It is advised to give StatCraft\n" +
        "its own database to work with, which is the simplest way to prevent conflicts.")
    var forceSetup: Boolean = false
)
