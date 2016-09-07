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

public class FiresStarted extends StatCraftStatistic {

    private static final FiresStarted instance = new FiresStarted();

    public static FiresStarted getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Fires Started";
    }

    @Override
    public int getIndex() {
        return 7;
    }
}
