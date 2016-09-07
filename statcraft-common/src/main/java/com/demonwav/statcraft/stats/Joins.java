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

public class Joins extends StatCraftStatistic {

    private static final Joins instance = new Joins();

    public static Joins getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Joins";
    }

    @Override
    public int getIndex() {
        return 11;
    }
}
