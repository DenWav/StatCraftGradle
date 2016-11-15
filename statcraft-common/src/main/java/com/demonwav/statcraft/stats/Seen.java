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
import com.demonwav.statcraft.api.StatCraftStatisticType;

import org.jetbrains.annotations.NotNull;

public class Seen extends StatCraftStatistic {

    private static final Seen instance = new Seen();

    public static Seen getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Seen";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return SeenStatisticType.values();
    }

    @Override
    public int getIndex() {
        return 20;
    }

    public enum SeenStatisticType implements StatCraftStatisticType {
        FIRST_JOIN_TIME("First Time Joined", 0),
        LAST_JOIN_TIME("Most Recent JOin", 1),
        LAST_LEAVE_TIME("Most Recent Leave", 2),
        LAST_SPOKEN_TIME("Most Recent Speech", 3);

        private final String name;
        private final int index;

        SeenStatisticType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        @NotNull
        @Override
        public String getName() {
            return null;
        }

        @Override
        public int getIndex() {
            return 0;
        }
    }
}
