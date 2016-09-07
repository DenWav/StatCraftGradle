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

public class FishCaught extends StatCraftStatistic {

    private static final FishCaught instance = new FishCaught();

    public static FishCaught getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Fish Caught";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return FishCaughtStatType.values();
    }

    @Override
    public int getIndex() {
        return 8;
    }

    public enum FishCaughtStatType implements StatCraftStatisticType {
        FISH("Fish Caught", 0),
        JUNK("Junk Caught", 1),
        TREASURE("Treasure Caught", 2);

        private final String name;
        private final int index;

        FishCaughtStatType(String name, int index) {
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
