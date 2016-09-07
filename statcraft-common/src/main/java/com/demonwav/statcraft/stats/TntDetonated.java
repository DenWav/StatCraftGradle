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

public class TntDetonated  extends StatCraftStatistic {

    private static final TntDetonated instance = new TntDetonated();

    public static TntDetonated getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "TNT Detonated";
    }

    @Override
    public int getIndex() {
        return 24;
    }
}
