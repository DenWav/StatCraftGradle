/*
 * StatCraft Plugin
 *
 * Copyright (c) 2016 Kyle Wood (DemonWav)
 * https://www.demonwav.com
 *
 * MIT License
 */

package com.demonwav.statcraft;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * A {@code promise} for data which may or may not come later. This is used for things such as database calls where
 * there may be some significant amount of time before a response is returned. This {@code promise} accepts a
 * {@link Consumer} which will always be run on the main thread either with the returned value, or an empty value, known
 * as an {@link #EMPTY_PROMISE()}. In all cases, including empty promises, the {@link Consumer} will be called back on
 * the main thread, so thread safety is ensured for calling other non-thread safe API calls.
 */
public interface Promise<T> {

    /**
     * Give the nullable value the {@code promise} should return to the provided {@link Consumer}. If no data is to be
     * set, either return {@link #EMPTY_PROMISE()}, give this method null, or call {@link #setError(String)} to print an error message along
     * with creating an {@code empty promise}.
     *
     * @param t The nullable value used to fulfill the promise. If {@code t} is null then this becomes an {@code empty promise}.
     */
    void setValue(@Nullable T t);

    /**
     * Print an error message to the console and turn this {@code promise} into an {@code empty promise}.
     * <p>
     * If you don't want to print an error message, and just return an {@code empty promise}, you should instead return
     * {@link #EMPTY_PROMISE()}.
     *
     * @param message The error message to print to the console.
     */
    void setError(@NotNull String message);

    /**
     * Provide the {@link Consumer} which should be run on the main thread with the result once the promise has been
     * fulfilled, or an empty optional in the case of an {@code emtpy promise}.
     *
     * @param work The {@link Consumer} to be run on the main thread.
     */
    void done(@NotNull Consumer<Optional<T>> work);

    /**
     * Return an {@code empty promise}, which runs the provided {@link Consumer} on the main thread with an empty
     * optional.
     *
     * @param <T> The type of the promise.
     * @return An {@code empty promise}.
     */
    static <T> Promise<T> EMPTY_PROMISE() {
        return new Promise<T>() {
            @Override
            public void setValue(T t) {}

            @Override
            public void setError(@NotNull String message) {}

            @Override
            public void done(@NotNull Consumer<Optional<T>> work) {
                StatCraft.getInstance().getThreadManager().scheduleMain(() -> work.accept(Optional.empty()));
            }
        };
    }
}
