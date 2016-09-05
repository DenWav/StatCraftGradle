/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft;

import com.demonwav.statcraft.api.StatCraftApi;
import com.demonwav.statcraft.commands.BaseCommand;
import com.demonwav.statcraft.config.Config;
import com.demonwav.statcraft.sql.DatabaseManager;
import com.demonwav.statcraft.sql.ThreadManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 */
public interface StatCraft {

    /**
     * TODO
     */
    static StatCraft getInstance() {
       return Companion.instance;
    }

    class Companion {
        static StatCraft instance = null;
    }

    /**
     * TODO
     * @return
     */
    @NotNull
    DatabaseManager getDatabaseManager();

    /**
     * TODO
     * @return
     */
    @NotNull
    ConcurrentHashMap<UUID, Integer> getLastFireTime();

    /**
     * TODO
     * @return
     */
    @NotNull
    ConcurrentHashMap<UUID, Integer> getlastDrownTime();

    /**
     * TODO
     * @return
     */
    @NotNull
    ConcurrentHashMap<UUID, Integer> getLastPoisonTime();

    /**
     * TODO
     * @return
     */
    @NotNull
    ConcurrentHashMap<UUID, Integer> getLastWitherTime();

    /**
     * TODO
     * @return
     */
    @NotNull
    ThreadManager getThreadManager();

    /**
     * TODO
     * @return
     */
    @NotNull
    BaseCommand getBaseCommand();

    /**
     * TODO
     * @return
     */
    @NotNull
    String getTimeZone();

    /**
     * TODO
     * @return
     */
    @NotNull
    ConcurrentHashMap<String, UUID> getPlayers();

    /**
     * TODO
     * @return
     */
    @NotNull
    ServerStatUpdater.Move<?, ?> getMoveUpdater();

    /**
     * TODO
     * @return
     */
    @NotNull
    Config getStatConfig();

    /**
     * TODO
     * @param uuid
     * @return
     */
    @Nullable
    String getPlayerName(@NotNull UUID uuid);

    /**
     * TODO
     * @param uuid
     * @return
     */
    @Nullable
    String getWorldName(@NotNull UUID uuid);

    /**
     * TODO
     */
    void disablePlugin();

    /**
     * TODO
     * @return
     */
    boolean isEnabled();

    /**
     * TODO
     * @param s
     */
    void info(@NotNull String s);

    /**
     * TODO
     * @param s
     */
    void warn(@NotNull String s);

    /**
     * TODO
     * @param s
     */
    void error(@NotNull String s);

    /**
     * TODO
     * @param plugin
     * @return
     */
    @NotNull
    StatCraftApi getApi(@NotNull Object plugin);
}