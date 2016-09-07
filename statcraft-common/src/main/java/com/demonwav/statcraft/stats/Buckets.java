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

public class Buckets extends StatCraftStatistic {

    private static final Buckets instance = new Buckets();

    public static Buckets getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Buckets";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return BucketStatType.values();
    }

    @Override
    public int getIndex() {
        return 2;
    }

    public enum BucketStatType implements StatCraftStatisticType {
        FILLED("Buckets Filled", 0),
        EMPTIED("Buckets Emptied", 1);

        private final String name;
        private final int index;

        BucketStatType(String name, int index) {
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
