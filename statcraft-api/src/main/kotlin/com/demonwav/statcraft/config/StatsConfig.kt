/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.config

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable

@ConfigSerializable
data class StatsConfig(
    @field:Setting(comment = "Record deaths")
    var deaths: Boolean = true,

    @field:Setting(comment = "Record when a player breaks/places a block.")
    var blocks: Boolean = true,
    @field:Setting(comment = "Allow players to search specific blocks broken on players (/mined command).")
    var specificBlocks: Boolean = true,

    @field:Setting(comment = "Keep track of how long players are online.\nlast_seen and joins require that this must be true.")
    var playTime: Boolean = true,
    @field:Setting(comment = "Allow players to check when a player was last online.\nplay_time must be true to enable this.")
    var lastSeen: Boolean = true,
    @field:Setting(comment = "Keep track of the number of times a player joins the server.\nplay_time must be true to enable this.")
    var joins: Boolean = true,
    @field:Setting(comment = "Record the date when a player first joined joined the server.")
    var firstJoinTime: Boolean = true,

    @field:Setting(comment = "Record number of items a player has crafted.")
    var itemsCrafted: Boolean = true,

    @field:Setting(comment = "Record number of items a player has brewed.")
    var itemsBrewed: Boolean = true,

    @field:Setting(comment = "Record number of items a player has cooked.")
    var itemsCooked: Boolean = true,

    @field:Setting(comment = "Record how long a player has been on fire.")
    var onFire: Boolean = true,
    @field:Setting(comment = "Announce to the server whenever a player is on fire.")
    var onFireAnnounce: Boolean = true,
    @field:Setting(comment = "Message to show to the players when an on_fire announcement is made.\n~ is the player's name.")
    var onFireAnnounceMessage: String = "~ is on fire! Oh no!",

    @field:Setting(comment = "Record number of times a player changes worlds (such as nether portals).")
    var worldChanges: Boolean = true,

    @field:Setting(comment = "Record when a player breaks a tool.")
    var toolsBroken: Boolean = true,

    @field:Setting(comment = "Record number of arrows shot.")
    var arrowsShot: Boolean = true,

    @field:Setting(comment = "Record when a player fills a bucket.")
    var bucketsFilled: Boolean = true,
    @field:Setting(comment = "Record whe na player empties a bucket.")
    var bucketsEmptied: Boolean = true,

    @field:Setting(comment = "Record when a player drops an item.")
    var itemDrops: Boolean = true,
    @field:Setting(comment = "Record when a player picks up an item.")
    var itemPickUps: Boolean = true,

    @field:Setting(comment = "Record the number of times a player enters and leaves a bed.\nThis includes time slept.")
    var bed: Boolean = true,

    @field:Setting(comment = "Record the number of messages a player has spoken.")
    var messagesSpoken: Boolean = true,
    @field:Setting(comment = "Record the total number of words a player has spoken.\nmessages_spoken must be true to enable this.")
    var wordsSpoken: Boolean = true,
    @field:Setting(comment = "Record specific words spoken by players.\nwords_spoken must be true to enable this.")
    var specificWordsSpoken: Boolean = true,

    @field:Setting(comment = "Record the number of attempted tab-completes a player does.")
    var tabCompletes: Boolean = true,

    @field:Setting(comment = "Record the total damage a player has received.")
    var damageTaken: Boolean = true,
    @field:Setting(comment = "Announce to the server whenever a player is drowning.")
    var drowningAnnounce: Boolean = true,
    @field:Setting(comment = "Announce to the server whenever a player is poisoned.")
    var poisonAnnounce: Boolean = true,
    @field:Setting(comment = "Announce to the server whenever a player is withering away.")
    var witherAnnounce: Boolean = true,
    @field:Setting(comment = "Message to show to the players when a drowning announcement is made.\n~ is the player's name.")
    var drownAnnounceMessage: String = "~ is drowning! Oh no!",
    @field:Setting(comment = "Message to show to the players when a poison announcement is made.\n~ is the player's name.")
    var poisonAnnounceMessage: String = "~ is poisoned! Oh no!",
    @field:Setting(comment = "Message to show to the players when a withering away announcement is made.\n~ is the player's name.")
    var witherAnnounceMessage: String = "~ is withering away! Oh no!",

    @field:Setting(comment = "Record when a player catches a fish.")
    var fishCaught: Boolean = true,

    @field:Setting(comment = "Record the total xp gained by a player.")
    var xpGained: Boolean = true,
    @field:Setting(comment = "Record the total xp spent by a player (enchanting, repairing).")
    var xpSpent: Boolean = true,
    @field:Setting(comment = "Record the highest leve a player has obtained.")
    var highestLevel: Boolean = true,

    @field:Setting(comment = "Keep track of how far a player has moved (uses server stats).")
    var move: Boolean = true,

    @field:Setting(comment = "Record when a player kills a mob or another player.")
    var kills: Boolean = true,

    @field:Setting(comment = "Record how many times a player has jumped.")
    var jumps: Boolean = true,

    @field:Setting(comment = "Track the number of eggs thrown.")
    var eggsThrown: Boolean = true,

    @field:Setting(comment = "Record stats on ender pearls.")
    var enderPearls: Boolean = true,
    @field:Setting(comment = "Record stats on snowballs.")
    var snowBalls: Boolean = true,

    @field:Setting(comment = "Record how many animals a player has killed.")
    var animalsBred: Boolean = true,

    @field:Setting(comment = "Record how many blocks of TNT a player has set off.")
    var tntDetonated: Boolean = true,

    @field:Setting(comment = "Record how many times a player has enchanted an item.")
    var enchantsDone: Boolean = true,

    @field:Setting(comment = "Record how many times a player has repaired an item.")
    var repairsDone: Boolean = true,

    @field:Setting(comment = "Track the amount of damage a player has dealt to mobs and other players.")
    var damageDealt: Boolean = true,

    @field:Setting(comment = "Track how many times a player has started a fire.")
    var firesStarted: Boolean = true,

    @field:Setting(comment = "Track how many times a player has been kicked and the reasons")
    var kicks: Boolean = true
)
