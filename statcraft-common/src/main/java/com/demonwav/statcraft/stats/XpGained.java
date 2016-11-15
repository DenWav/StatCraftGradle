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

public class XpGained  extends StatCraftStatistic {

    private static final XpGained instance = new XpGained();

    public static XpGained getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "XP Gained";
    }

    @Override
    public int getIndex() {
        return 28;
    }
}
