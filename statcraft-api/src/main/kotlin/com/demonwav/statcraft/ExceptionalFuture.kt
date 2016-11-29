package com.demonwav.statcraft

import java.util.concurrent.CompletableFuture

/**
 * A convenience class for creating completable futures which have completed exceptionally.
 */
object ExceptionalFuture {
    fun <T> create(t: Throwable): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        future.completeExceptionally(t)
        return future
    }
}
