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

public class Deaths extends StatCraftStatistic {

    private static final Deaths instance = new Deaths();

    public static Deaths getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Deaths";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return DeathStatType.values();
    }

    @Override
    public int getIndex() {
        return 5;
    }

    public enum DeathStatType implements StatCraftStatisticType {
        CAUSE("Death Messages", 0),
        MESSAGE("Death Causes", 1);

        private final String name;
        private final int index;

        DeathStatType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        @NotNull
        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getIndex() {
            return index;
        }
    }
}
