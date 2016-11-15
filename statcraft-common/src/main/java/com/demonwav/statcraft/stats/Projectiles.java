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

public class Projectiles extends StatCraftStatistic {

    private static final Projectiles instance = new Projectiles();

    public static Projectiles getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Projectiles";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return ProjectileEntityStatType.values();
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getSecondaryStatTypes() {
        return ProjectilesStatType.values();
    }

    @Override
    public int getIndex() {
        return 19;
    }

    public enum ProjectileEntityStatType implements StatCraftStatisticType {
        NORMAL_ARROW("Normal Arrows", 0),
        FLAMING_ARROW("Blocks Broken", 1),
        ENDER_PEARL("Blocks Broken", 2),
        UNHATCHED_EGG("Blocks Broken", 3),
        HATCHED_EGG("Blocks Broken", 4),
        FOUR_HATCHED_EGG("Blocks Broken", 5),
        SNOWBALL("Blocks Broken", 6);

        private final String name;
        private final int index;

        ProjectileEntityStatType(String name, int index) {
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

    public enum ProjectilesStatType implements StatCraftStatisticType {
        TOTAL_DISTANCE("Total Distance", 0),
        MAX_DISTANCE("Maximum Distance", 1),
        AMOUNT("Amount", 2);

        private final String name;
        private final int index;

        ProjectilesStatType(String name, int index) {
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
