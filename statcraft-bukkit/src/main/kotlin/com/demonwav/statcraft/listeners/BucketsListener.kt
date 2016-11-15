package com.demonwav.statcraft.listeners

import com.demonwav.statcraft.stats.Buckets
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

class BucketsListener : Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBucketFill(event: PlayerBucketFillEvent) {
        Buckets.getInstance().query().primaryType(Buckets.BucketStatType.FILLED)
            .incrementValue(event.player.uniqueId, event.player.world.uid, event.bucket.name)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBucketEmpty(event: PlayerBucketEmptyEvent) {
        Buckets.getInstance().query().primaryType(Buckets.BucketStatType.EMPTIED)
            .incrementValue(event.player.uniqueId, event.player.world.uid, event.bucket.name)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onMilkBucketEmpty(event: PlayerItemConsumeEvent) {
        if (event.item.type == Material.MILK_BUCKET) {
            Buckets.getInstance().query().primaryType(Buckets.BucketStatType.EMPTIED)
                .incrementValue(event.player.uniqueId, event.player.world.uid, Material.MILK_BUCKET.name)
        }
    }
}
