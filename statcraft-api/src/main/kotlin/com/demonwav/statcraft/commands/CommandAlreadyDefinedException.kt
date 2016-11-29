/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.commands

/**
 * Exception thrown by [BaseCommand] when a StatCraft command is declared with the same name as a previously defined command.
 */
class CommandAlreadyDefinedException(s: String) : RuntimeException("$s has already been defined.")
