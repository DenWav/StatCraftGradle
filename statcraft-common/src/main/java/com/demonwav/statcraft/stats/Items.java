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

public class Items extends StatCraftStatistic {

    private static final Items instance = new Items();

    public static Items getInstance() {
        return instance;
    }

    @NotNull
    @Override
    public String getName() {
        return "Items";
    }

    @NotNull
    @Override
    public StatCraftStatisticType[] getPrimaryStatTypes() {
        return ItemStatType.values();
    }

    @Override
    public int getIndex() {
        return 10;
    }

    public enum ItemStatType implements StatCraftStatisticType {
        ENCHANTED("Items Enchanted", 0),
        DROPPED("Items Dropped", 1),
        PICKEDUP("Items Picked Up", 2),
        BREWED("Items Brewed", 3),
        COOKED("Items Cooked", 4),
        CRAFTED("Items Crafted", 5);

        private final String name;
        private final int index;

        ItemStatType(String name, int index) {
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
