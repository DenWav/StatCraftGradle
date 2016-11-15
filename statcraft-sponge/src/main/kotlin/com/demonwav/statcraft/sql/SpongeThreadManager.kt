/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.demonwav.statcraft.SpongeStatCraft
import org.spongepowered.api.Sponge

class SpongeThreadManager : AbstractThreadManager() {
    override fun shutdown() {
        Sponge.getScheduler().getScheduledTasks(SpongeStatCraft.instance.plugin).forEach { it.cancel() }
    }
}
