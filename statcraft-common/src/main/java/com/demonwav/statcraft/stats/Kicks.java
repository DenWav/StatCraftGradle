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

public class Kicks extends StatCraftStatistic {

    private static final Kicks instance = new Kicks();

    public static Kicks getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Kicks";
    }

    @Override
    public int getIndex() {
        return 13;
    }
}
