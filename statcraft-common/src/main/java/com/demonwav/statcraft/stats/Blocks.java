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

public class Blocks extends StatCraftStatistic {

    private static final Blocks instance = new Blocks();

    public static Blocks getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Blocks";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return BlockStatType.values();
    }

    @Override
    public int getIndex() {
        return 1;
    }

    public enum BlockStatType implements StatCraftStatisticType {
        PLACED("Blocks Placed", 0),
        BROKEN("Blocks Broken", 1);

        private final String name;
        private final int index;

        BlockStatType(String name, int index) {
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
