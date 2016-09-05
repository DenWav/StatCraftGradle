/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */
@file:JvmName("ExtFunctions")

package com.demonwav.statcraft

import java.nio.ByteBuffer
import java.util.UUID

inline fun <T : AutoCloseable> T.use(block: (T) -> Unit) {
    var closed = false
    try {
        block(this)
    } catch (e: Exception) {
        closed = true
        try {
            close()
        } catch (closeException: Exception) {}
    } finally {
        if (!closed) {
            close()
        }
    }
}

fun UUID.toByte(): ByteArray {
    val byteBuffer = ByteBuffer.wrap(ByteArray(16))
    byteBuffer.putLong(mostSignificantBits)
    byteBuffer.putLong(leastSignificantBits)
    return byteBuffer.array()
}

fun ByteArray.toUUID(): UUID {
    val buffer = ByteBuffer.wrap(this)
    return UUID(buffer.long, buffer.long)
}

inline fun <T> MutableIterable<T>.iter(func: MutableIterator<T>.(T) -> Unit) {
    val iter = iterator()

    while (iter.hasNext()) {
        val item = iter.next()
        iter.func(item)
    }
}
