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

public class ToolsBroken extends StatCraftStatistic {

    private static final ToolsBroken instance = new ToolsBroken();

    public static ToolsBroken getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Tools Broken";
    }

    @Override
    public int getIndex() {
        return 25;
    }
}
