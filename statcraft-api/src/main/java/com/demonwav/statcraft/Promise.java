/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft;

import java.util.function.Consumer;

/**
 * TODO
 */
public interface Promise<T> {

    /**
     * TODO
     * @param t
     */
    void setValue(T t);

    /**
     * TODO
     * @param message
     */
    void setError(String message);

    /**
     * TODO
     * @param work
     */
    void done(Consumer<T> work);

    /**
     * TODO
     * @param <T>
     * @return
     */
    static <T> Promise<T> EMPTY_PROMISE() {
        return new Promise<T>() {
            @Override
            public void setValue(T t) {}

            @Override
            public void setError(String message) {}

            @Override
            public void done(Consumer<T> work) {
                StatCraft.getInstance().getThreadManager().scheduleMain(() -> work.accept(null));
            }
        };
    }
}
