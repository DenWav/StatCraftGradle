/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import com.google.inject.Inject
import org.slf4j.Logger
import org.spongepowered.api.config.DefaultConfig
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePostInitializationEvent
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent
import org.spongepowered.api.plugin.Plugin
import java.io.File
import java.nio.file.Path

@Plugin(
    id = "statcraft",
    name = "StatCraft",
    version = "1.0-SNAPSHOT"
)
class StatCraftPlugin {

    @Inject
    lateinit var logger: Logger

    @Inject
    @DefaultConfig(sharedRoot = false)
    lateinit var defaultConfig: Path

    init {
        SpongeStatCraft(this)
    }

    @Listener
    fun onGamePreInitializationEvent(event: GamePreInitializationEvent) {
        SpongeStatCraft.instance.preInit()
    }

    @Listener
    fun onGamePostInitializationEvent(event: GamePostInitializationEvent) {
        SpongeStatCraft.instance.postInit()
    }

    @Listener
    fun onGameStoppingEvent(event: GameStoppingEvent) {
        SpongeStatCraft.instance.shutdown()
    }
}
