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
 * TODO
 */
interface ServerStatUpdater {

    /**
     * TODO
     */
    interface Move<in P : Any?, in W : Any?> : Runnable {
        /**
         * TODO
         */
        override fun run()

        /**
         * TODO
         */
        fun run(player: P)

        /**
         * TODO
         */
        fun run(player: P, world: W)
    }
}
