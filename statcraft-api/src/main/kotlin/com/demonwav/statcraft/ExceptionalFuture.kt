package com.demonwav.statcraft

import java.util.concurrent.CompletableFuture

object ExceptionalFuture {
    fun <T> create(t: Throwable): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        future.completeExceptionally(t)
        return future
    }
}
