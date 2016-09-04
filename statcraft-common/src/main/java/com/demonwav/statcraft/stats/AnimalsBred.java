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

public class AnimalsBred extends StatCraftStatistic {

    private static final AnimalsBred instance = new AnimalsBred();

    public static AnimalsBred getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Animals Bred";
    }

    @Override
    public int getIndex() {
        return 0;
    }
}
