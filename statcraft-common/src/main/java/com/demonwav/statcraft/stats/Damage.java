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

public class Damage extends StatCraftStatistic {

    private static final Damage instance = new Damage();

    public static Damage getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Damage";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return DamageStatType.values();
    }

    @Override
    public int getIndex() {
        return 3;
    }

    public enum DamageStatType implements StatCraftStatisticType {
        DEALT("Damage Dealt", 0),
        TAKEN("Damage Taken", 1);

        private final String name;
        private final int index;

        DamageStatType(String name, int index) {
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
