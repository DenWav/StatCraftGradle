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

public class Eating extends StatCraftStatistic {

    private static final Eating instance = new Eating();

    public static Eating getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Eating";
    }

    @Override
    public int getIndex() {
        return 6;
    }
}
