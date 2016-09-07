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

public class Jumps extends StatCraftStatistic {

    private static final Jumps instance = new Jumps();

    public static Jumps getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Jumps";
    }

    @Override
    public int getIndex() {
        return 12;
    }
}
