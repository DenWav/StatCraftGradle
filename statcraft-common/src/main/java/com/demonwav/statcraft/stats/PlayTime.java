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

public class PlayTime extends StatCraftStatistic {

    private static final PlayTime instance = new PlayTime();

    public static PlayTime getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Play Time";
    }

    @Override
    public int getIndex() {
        return 18;
    }
}
