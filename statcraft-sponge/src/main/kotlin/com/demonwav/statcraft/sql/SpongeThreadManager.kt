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
    override fun startExecutors() {
        Sponge.getScheduler()
            .createTaskBuilder().delayTicks(1).intervalTicks(1).execute(main).submit(SpongeStatCraft.instance.plugin)
        Sponge.getScheduler()
            .createTaskBuilder().delayTicks(1).intervalTicks(1).async().execute(async).submit(SpongeStatCraft.instance.plugin)
    }
}
