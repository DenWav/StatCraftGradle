/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import java.util.Optional
import java.util.function.Consumer

class PromiseImpl<T : Any> : Promise<T> {

    var work: Consumer<Optional<T>>? = null

    override fun setValue(t: T?) {
        // Don't bother executing if no work was set
        if (work != null) {
            StatCraft.getInstance().threadManager.scheduleMain(Runnable { work?.accept(Optional.of(t)) })
        }
    }

    override fun setError(message: String) {
        StatCraft.getInstance().error(message)
        if (work != null) {
            StatCraft.getInstance().threadManager.scheduleMain(Runnable { work?.accept(Optional.empty()) })
        }
    }

    override fun done(work: Consumer<Optional<T>>) {
        this.work = work
    }
}
