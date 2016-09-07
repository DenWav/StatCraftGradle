/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft

import java.util.function.Consumer

class PromiseImpl<T> : Promise<T> {

    var work: Consumer<T>? = null

    override fun setValue(t: T?) {
        // Don't bother executing if no work was set
        if (work != null) {
            StatCraft.getInstance().threadManager.scheduleMain(Runnable { work?.accept(t) })
        }
    }

    override fun setError(message: String) {
        work = null
        StatCraft.getInstance().error(message)
    }

    override fun done(work: Consumer<T>) {
        this.work = work
    }
}
