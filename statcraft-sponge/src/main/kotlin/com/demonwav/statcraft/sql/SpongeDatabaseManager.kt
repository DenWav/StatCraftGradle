/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

class SpongeDatabaseManager : AbstractDatabaseManager() {
    // We don't have access to commons lang < 3, so we don't have escapeSql, so just don't worry about it
    // Our use case is only for where we can't user prepared statements, and it's already hardcoded on our input
    // (they are our column names) so we don't actually have anything to worry about
    override fun escapeSql(s: String) = s
}