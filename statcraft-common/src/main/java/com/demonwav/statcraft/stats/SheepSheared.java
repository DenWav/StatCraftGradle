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
import com.demonwav.statcraft.api.StatCraftStatisticType;

import org.jetbrains.annotations.NotNull;

public class SheepSheared extends StatCraftStatistic {

    private static final SheepSheared instance = new SheepSheared();

    public static SheepSheared getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Sheep Sheared";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return new StatCraftStatisticType[0];
    }

    @Override
    public int getIndex() {
        return 21;
    }
}
