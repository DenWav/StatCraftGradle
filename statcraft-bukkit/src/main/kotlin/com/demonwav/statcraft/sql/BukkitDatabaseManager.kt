/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import org.apache.commons.lang.StringEscapeUtils

class BukkitDatabaseManager : AbstractDatabaseManager() {
    override fun escapeSql(s: String): String = StringEscapeUtils.escapeSql(s)
}
