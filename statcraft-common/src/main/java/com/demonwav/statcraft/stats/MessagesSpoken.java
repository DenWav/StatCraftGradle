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

public class MessagesSpoken extends StatCraftStatistic {

    private static final MessagesSpoken instance = new MessagesSpoken();

    public static MessagesSpoken getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Messages Spoken";
    }

    @Override
    public int getIndex() {
        return 15;
    }
}
