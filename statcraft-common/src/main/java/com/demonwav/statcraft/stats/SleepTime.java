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

public class SleepTime extends StatCraftStatistic {

    private static final SleepTime instance = new SleepTime();

    public static SleepTime getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Time Slept";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return SleepStatisticType.values();
    }

    @Override
    public int getIndex() {
        return 22;
    }

    public enum SleepStatisticType implements StatCraftStatisticType {
        ENTER_BED("Most Recent Bed Enter", 0),
        LEAVE_BED("Most Recent Bed Leave", 1),
        TIME_SLEPT("Time Slept", 2);

        private final String name;
        private final int index;

        SleepStatisticType(String name, int index) {
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
