/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft.sql

import com.demonwav.statcraft.StatCraft

class PromiseImpl<T> : Promise<T> {

    var work: ((T?) -> Unit)? = null

    override fun setValue(t: T?) {
        // Don't bother executing if no work was set
        if (work != null) {
            StatCraft.getInstance().threadManager.scheduleMain(Runnable { work?.invoke(t) })
        }
    }

    override fun setError(message: String) {
        work = null
        StatCraft.getInstance().error(message)
    }

    override fun done(work: (T?) -> Unit) {
        this.work = work
    }
}
