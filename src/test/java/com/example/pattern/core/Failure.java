package com.example.pattern.core;

import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class Failure<A> implements Try<A> {
    private final Object error;

    public Failure(Object error) {
        this.error = error;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @Override
    public <B> Try<B> flatMap(Function<A, Try<B>> fn) {
        return new Failure<>(error);
    }

    @Override
    public <B> Try<B> map(Function<A, B> fn) {
        return new Failure<>(error);
    }

    @Override
    public A get() {
        return null;
    }
}
