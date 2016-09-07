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

public class Kills extends StatCraftStatistic {

    private static final Kills instance = new Kills();

    public static Kills getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Kills";
    }

    @Override
    public int getIndex() {
        return 14;
    }
}
