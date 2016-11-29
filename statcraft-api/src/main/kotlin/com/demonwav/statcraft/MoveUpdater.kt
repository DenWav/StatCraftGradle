/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

/**
 * This class is responsible for reading the player movement statistics managed by the Minecraft Server for each player and storing it
 * in the database. There is no contract as to how often this class will be run to update the entries in the database, but in practice it's
 * usually every 40 ticks, or about once every 2 seconds. Implementations are free to make this value configurable as well. This class is
 * generally not intended to be implemented by clients, if you want the instance of this class to manually run the update, retrieve it with
 * [StatCraft.getInstance().getMoveUpdater()][com.demonwav.statcraft.StatCraft.getMoveUpdater].
 */
interface MoveUpdater<in P, in W> : Runnable {

    /**
     * Called regularly by the implementation. Updates all movement statistics for all players in all worlds.
     */
    override fun run()

    /**
     * Usually called by the [run] method. Updates all movement statistics for the specified player for all worlds.
     *
     * @param player The player to update statistics on
     */
    fun run(player: P)

    /**
     * Usually called by the [run] method. Updates all movement statistics for the specified player in the specified world.
     *
     * @param player The player to update statistics on
     * @param world The world to update statistics on
     */
    fun run(player: P, world: W)
}