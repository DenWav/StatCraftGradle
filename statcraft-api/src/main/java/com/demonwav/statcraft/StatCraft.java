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

import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

/**
 * This is the main StatCraft global API, which defines accessors for other parts of the StatCraft API, such as the
 * {@link StatCraftApi plugin instance api}, {@link ThreadManager thread manager},
 * {@link DatabaseManager database manager}, and {@link Config StatCraft config}, and others.
 * <p>
 * The {@code StatCraft} instance should be retrieved with the {@link #getInstance()} static method.
 * <p>
 * <b>This is not the plugin instance.</b> The {@link StatCraft} object and the object which extends {@code JavaPlugin}
 * in Bukkit, or is annotated with {@code @Plugin} in Sponge are different objects.
 */
public interface StatCraft {

    /**
     * Retrieve the current global StatCraft instance. This is a direct variable access method, so it is not expensive
     * and can be used freely. This is the recommended way of obtaining the StatCraft instance from any context.
     *
     * @return The current global StatCraft instance.
     */
    @NotNull
    static StatCraft getInstance() {
       return Companion.instance;
    }

    class Companion {
        static StatCraft instance = null;
    }

    /**
     * Retrieve the current global {@link DatabaseManager} instance.
     *
     * @return The current global {@link DatabaseManager} instance.
     */
    @NotNull
    DatabaseManager getDatabaseManager();

    /**
     * Retrieve the current global {@link ThreadManager} instance.
     *
     * @return The current global {@link ThreadManager} instance.
     */
    @NotNull
    ThreadManager getThreadManager();

    /**
     * Retrieve the current global {@link BaseCommand} instance.
     *
     * @return The current global {@link BaseCommand} instance.
     */
    @NotNull
    BaseCommand getBaseCommand();

    /**
     * Retrieve the three character timezone code either defined in the config or automatically determined if set as
     * 'auto' in the configuration file.
     *
     * @return The three character timezone code for use in printing dates and times.
     */
    @NotNull
    String getTimeZone();

    /**
     * Retrieve the player name to UUID mapping loaded from the database. This means it contains player name to UUID
     * mappings for all players in the StatCraft database, not just players who are online. The returned map is an
     * immutable view of the map.
     *
     * @return The player to UUID mapping from the StatCraft database.
     */
    @NotNull
    Map<String, UUID> getPlayers();

    /**
     * Retrieve the current global {@link MoveUpdater} instance.
     *
     * @return The current global {@link MoveUpdater} instance.
     */
    @NotNull
    MoveUpdater<?, ?> getMoveUpdater();

    /**
     * Retrieve the contents of the StatCraft configuration file as a data class. This can only safely be called in
     * {@code onEnable()} or later for Bukkit and {@code GamePostInitializationEvent} or later for Sponge, since it is
     * defined in {@code onLoad()} in Bukkit and {@code GamePreInitializationEvent} in Sponge.
     * <p>
     * The {@code @NotNull} contract on this method only applies when it is called with the above contract. If called
     * before that, no guarantees of nullability can be made.
     * <p>
     * Due to the existence of a publicly facing {@link #setStatConfig(Config)} method, there is no guarantee the
     * {@link Config} object you receive from one call will be the currently used configuration the next time you access
     * it, so you should always call this method to retreive the {@link Config} object any time you are using it.
     *
     * @return The contents of the StatCraft configuration file as a data class.
     */
    @NotNull
    Config getStatConfig();

    /**
     * Retrieve the {@link Path} object that points to the location of the StatCraft configuration file.
     *
     * @return The {@link Path} object which points to the location of the StatCraft config file.
     */
    @NotNull
    Path getConfigFile();

    /**
     * Set the config to a different {@link Config} object. This will be saved during plugin shutdown. This is generally
     * used internally in StatCraft, if you need to change a configuration value, you should do so in the original
     * {@link Config} object, rather than pointing it to a new {@link Config} object. You should generally never use
     * this method, but it is available for use if you need to be that evil.
     *
     * @param config The new {@link Config} object that should replace the current configuration.
     */
    void setStatConfig(@NotNull Config config);

    /**
     * Given a player's UUID, StatCraft will attempt to find their name and return it. It will return null if it fails
     * to find the player's name.
     *
     * @param uuid The player's UUID to find.
     * @return The player's currently known name, or null if not found.
     */
    @Nullable
    String getPlayerName(@NotNull UUID uuid);

    /**
     * Given a world's UUID, StatCraft will attempt to find it's name and return it. It will return null if it fails
     * to find the world's name.
     *
     * @param uuid The world's UUID to find.
     * @return The world's current name, or null if not found.
     */
    @Nullable
    String getWorldName(@NotNull UUID uuid);

    /**
     * Tell the StatCraft plugin to disable itself. All StatCraft events, listeners, and commands will be disabled, and
     * all StatCraft API calls will do nothing. This cannot be undone once it is called without restarting the server.
     * This is another case where you should probably never do this, it is generally only used internally by StatCraft,
     * but it is publicly available if you need to be evil like that.
     */
    void disablePlugin();

    /**
     * Get the current status of whether the StatCraft plugin is enabled or not.
     *
     * @return True if StatCraft is currently enabled, false if otherwise.
     */
    boolean isEnabled();

    /**
     * Print an {@code info} message as StatCraft using the current StatCraft logger instance. This method exists
     * because Bukkit and Sponge use different {@code Logger} instances.
     *
     * @param s The message to log at log level {@code info}.
     */
    void info(@NotNull String s);

    /**
     * Print an {@code warn} message as StatCraft using the current StatCraft logger instance. This method exists
     * because Bukkit and Sponge use different {@code Logger} instances.
     *
     * @param s The message to log at log level {@code warn}.
     */
    void warn(@NotNull String s);

    /**
     * Print an {@code error} message as StatCraft using the current StatCraft logger instance. This method exists
     * because Bukkit and Sponge use different {@code Logger} instances.
     *
     * @param s The message to log at log level {@code error}.
     */
    void error(@NotNull String s);

    /**
     * Retrieve the {@link StatCraftApi} instance relevant to the given {@code plugin} object. The {@code plugin} object
     * can be anything as long as it is annotated with
     * {@link com.demonwav.statcraft.api.StatCraftNamespace StatCraftNamespace}. If the given object has already been
     * used to call this method, it will return the same {@link StatCraftApi} instance which was created the first time
     * this method was called.
     * <p>
     * This is what a plugin will use to define new
     * {@link com.demonwav.statcraft.api.StatCraftStatistic StatCraftStatistics}, and other such things.
     * <p>
     * If The given namespace {@link UUID} has already been used by a different object, this
     * method will throw a
     * {@link com.demonwav.statcraft.api.exceptions.StatCraftNamespaceAlreadyDefinedException StatCraftNamespaceAlreadyDefinedException}.
     * <p>
     * If the given object is not annotated correctly then this method will throw a
     * {@link com.demonwav.statcraft.api.exceptions.StatCraftNamespaceNotDefinedException StatCraftNamespaceNotDefinedException}.
     *
     * @param plugin The object annotated with {@link com.demonwav.statcraft.api.StatCraftNamespace StatCraftNamespace}
     *               which defines a plugin that is implementing the StatCraft API.
     * @return The {@link StatCraftApi} instance that corresponds with the given plugin.
     */
    @NotNull
    StatCraftApi getApi(@NotNull Object plugin);
}
