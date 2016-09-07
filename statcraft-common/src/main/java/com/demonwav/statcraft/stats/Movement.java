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

public class Movement extends StatCraftStatistic {

    private static final Movement instance = new Movement();

    public static Movement getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Movement";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return MovementStatType.values();
    }

    @Override
    public int getIndex() {
        return 16;
    }

    public enum MovementStatType implements StatCraftStatisticType {
        WALKING("Distance Walked", 0),
        CROUCHING("Distance Crouched", 1),
        SPRINTING("Distance Sprinted", 2),
        SWIMMING("Distance Swam", 3),
        FALLING("Distance Fallen", 4),
        CLIMBING("Distance Climbed", 5),
        FLYING("Distance Flown", 6),
        DIVING("Distance Dove", 7),
        MINECART("Distance Riding Minecarts", 8),
        BOAT("Distance Riding Boats", 9),
        PIG("Distance Riding Pigs", 10),
        HORSE("Distance Riding Horses", 11),
        ELYTRA("Distance Flown With Elytras", 12);

        private final String name;
        private final int index;

        MovementStatType(String name, int index) {
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
