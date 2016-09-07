/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.stats;

import com.demonwav.statcraft.api.StatCraftStatistic;
import org.jetbrains.annotations.NotNull;

public class WorldChanges extends StatCraftStatistic {

    private static final WorldChanges instance = new WorldChanges();

    public static WorldChanges getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "World Changes";
    }

    @Override
    public int getIndex() {
        return 27;
    }
}
