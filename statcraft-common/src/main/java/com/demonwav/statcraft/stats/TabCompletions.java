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

public class TabCompletions extends StatCraftStatistic {

    private static final TabCompletions instance = new TabCompletions();

    public static TabCompletions getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Tab Completions";
    }

    @Override
    public int getIndex() {
        return 23;
    }
}
