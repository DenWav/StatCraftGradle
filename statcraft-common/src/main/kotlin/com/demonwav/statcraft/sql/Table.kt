/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import org.intellij.lang.annotations.Language

enum class Table(@Language("MySQL") val create: String, val columnNames: List<String>) {

    PLAYERS(
        //language=MySQL
        """
        CREATE TABLE IF NOT EXISTS `players` (
          `uuid` binary(16) NOT NULL,
          `name` varchar(16) NOT NULL,
          `id` int(11) NOT NULL AUTO_INCREMENT,
          PRIMARY KEY (`id`),
          UNIQUE KEY `players_uuid_uindex` (`uuid`),
          UNIQUE KEY `players_name_uindex` (`name`),
          UNIQUE KEY `players_id_uindex` (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8
        """.trimIndent(),
        listOf("uuid", "name", "id")),


    WORLDS(
        //language=MySQL
        """
        CREATE TABLE IF NOT EXISTS `worlds` (
          `id` int(11) NOT NULL AUTO_INCREMENT,
          `uuid` binary(16) NOT NULL,
          `name` varchar(255) NOT NULL,
          `custom_name` varchar(255) NOT NULL,
          PRIMARY KEY (`id`),
          UNIQUE KEY `worlds_id_uindex` (`id`),
          UNIQUE KEY `worlds_uuid_uindex` (`uuid`),
          KEY `worlds_name_index` (`name`),
          KEY `worlds_custom_name_index` (`custom_name`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8
        """.trimIndent(),
        listOf("id", "uuid", "name", "custom_name")),


    NAMESPACE(
        //language=MySQL
        """
        CREATE TABLE `namespace` (
          `uuid` binary(16) NOT NULL,
          `id` int(11) NOT NULL AUTO_INCREMENT,
          PRIMARY KEY (`id`),
          UNIQUE KEY `namespace_uuid_uindex` (`uuid`),
          UNIQUE KEY `namespace_id_uindex` (`id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8
        """.trimIndent(),
        listOf("uuid", "id")
    ),


    STATS(
        //language=MySQL
        """
        CREATE TABLE IF NOT EXISTS `stats` (
          `namespace_id` int(11) NOT NULL,
          `stat_id` int(11) NOT NULL,
          `player_id` int(11) NOT NULL,
          `world_id` int(11) NOT NULL,
          `primary_stat_type_id` int(11) NOT NULL,
          `secondary_stat_type_id` int(11) NOT NULL,
          `primary_stat_target` varchar(255) NOT NULL,
          `secondary_stat_target` varchar(255) NOT NULL,
          `value` bigint(20) NOT NULL,
          PRIMARY KEY (`namespace_id`,`stat_id`,`player_id`,`world_id`,`primary_stat_type_id`,`secondary_stat_type_id`,`primary_stat_target`,`secondary_stat_target`),
          KEY `player_index` (`player_id`),
          KEY `world_index` (`world_id`),
          KEY `namespace_index` (`namespace_id`),
          CONSTRAINT `stats_namespace_id_fk` FOREIGN KEY (`namespace_id`) REFERENCES `namespace` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
          CONSTRAINT `stats_players_id_fk` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
          CONSTRAINT `stats_worlds_id_fk` FOREIGN KEY (`world_id`) REFERENCES `worlds` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8
        """.trimIndent(),
        listOf("namespace_id", "stat_id", "player_id", "world_id", "primary_stat_type_id", "secondary_stat_type_id", "primary_stat_target", "secondary_stat_target", "value")),


    CACHED_STATS(
        //language=MySQL
        """
        CREATE TABLE IF NOT EXISTS `cached_stats` (
          `namespace_id` int(11) NOT NULL,
          `stat_id` int(11) NOT NULL,
          `player_id` int(11) NOT NULL,
          `world_id` int(11) NOT NULL,
          `primary_stat_type_id` int(11) NOT NULL,
          `secondary_stat_type_id` int(11) NOT NULL,
          `primary_stat_target` int(11) NOT NULL,
          `secondary_stat_target` int(11) NOT NULL,
          `value` bigint(20) NOT NULL,
          PRIMARY KEY (`namespace_id`,`stat_id`,`player_id`,`world_id`,`primary_stat_type_id`,`secondary_stat_type_id`,`primary_stat_target`,`secondary_stat_target`),
          KEY `player_index` (`player_id`),
          KEY `world_index` (`world_id`),
          KEY `namespace_index` (`namespace_id`),
          KEY `1` (`namespace_id`,`stat_id`,`primary_stat_type_id`,`secondary_stat_type_id`,`primary_stat_target`,`secondary_stat_target`),
          KEY `2` (`namespace_id`,`stat_id`,`world_id`,`primary_stat_type_id`,`secondary_stat_type_id`,`primary_stat_target`,`secondary_stat_target`),
          CONSTRAINT `stats_namespace_id_fk` FOREIGN KEY (`namespace_id`) REFERENCES `namespace` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
          CONSTRAINT `cached_stats_players_id_fk` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT ,
          CONSTRAINT `cached_stats_worlds_id_fk` FOREIGN KEY (`world_id`) REFERENCES `worlds` (`id`) ON UPDATE CASCADE ON UPDATE RESTRICT
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8
        """.trimIndent(),
        listOf("namespace_id", "stat_id", "player_id", "world_id", "primary_stat_type_id", "secondary_stat_type_id", "primary_stat_target", "secondary_stat_target", "value")
    );

    fun getName() = name.toLowerCase()
    fun getColumnCount() = columnNames.size
}
