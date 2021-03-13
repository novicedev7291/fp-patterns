package com.example.pattern.core;

import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class TryReader<R, S> {
    private final Function<R, Try<S>> fn;

    public TryReader(Function<R, Try<S>> fn) {
        this.fn = fn;
    }

    public <T> TryReader<R, T> flatMap(Function<S, Try<T>> f) {
        return new TryReader<>((R r) -> apply(r).flatMap(f));
    }

    public <T> TryReader<R, T> map(Function<S, T> fn) {
        return new TryReader<>((R r) -> apply(r).map(fn));
    }

    public Try<S> apply(R r) {
        return fn.apply(r);
    }
}
