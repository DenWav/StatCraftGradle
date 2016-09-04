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

public class HighestLevel extends StatCraftStatistic {

    private static final HighestLevel instance = new HighestLevel();

    public static HighestLevel getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Highest Level";
    }

    @Override
    public int getIndex() {
        return 9;
    }
}
