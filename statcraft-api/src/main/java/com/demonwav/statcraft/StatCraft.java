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

import java.util.HashMap;
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
     * Returns the last time a player was on fire

     * @param uuid The uuid of the player that was on fire
     * @return The time that the player was last on fire
     */
    int getLastFireTime(UUID uuid);
    /**
     * Returns the last time a player was drowning

     * @param uuid The uuid of the player that was drowning
     * @return The time that the player was last drowning
     */
    int getLastDrownTime(UUID uuid);
    /**
     * Returns the last time a player was poisoned

     * @param uuid The uuid of the player that was poisoned
     * @return The time that the player was last poisoned
     */
    int getLastPoisonTime(UUID uuid);
    /**
     * Returns the last time a player was withering away

     * @param uuid The uuid of the player that was withering away
     * @return The time that the player was last withering away
     */
    int getLastWitherTime(UUID uuid);

    /**
     * Sets the last time a player is on fire

     * @param uuid The uuid of the player that is on fire
     * @param time The time the player was on fire
     */
    void setLastFireTime(UUID uuid, int time);
    /**
     * Sets the last time a player is drowning

     * @param uuid The uuid of the player that is drowning
     * @param time The time the player was drowning
     */
    void setLastDrowningTime(UUID uuid, int time);
    /**
     * Sets the last time a player is poisoned

     * @param uuid The uuid of the player that is poisoned
     * @param time The time the player was poisoned
     */
    void setLastPoisonTime(UUID uuid, int time);
    /**
     * Sets the last time a player is withering away

     * @param uuid The uuid of the player that is withering away
     * @param time The time the player is withering away
     */
    void setLastWitherTime(UUID uuid, int time);

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
    MoveUpdater<?, ?> getMoveUpdater();

    /**
     * TODO
     * @return
     */
    @NotNull
    Config getStatConfig();

    /**
     *
     * @param config
     */
    void setStatConfig(@NotNull Config config);

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

    /**
     * TODO
     */
    void preInit();

    /**
     * TODO
     */
    void postInit();

    /**
     * TODO
     */
    void shutdown();
}
