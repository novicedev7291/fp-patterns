package com.example.pattern.core;

import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class Success<A> implements Try<A> {
    private final A a;

    private Success(A a) {
        this.a = a;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public <B> Try<B> flatMap(Function<A, Try<B>> fn) {
        return fn.apply(a);
    }

    @Override
    public <B> Try<B> map(Function<A, B> fn) {
        return new Success<>(fn.apply(a));
    }

    public static <A> Try<A> of(A a) {
        return new Success<>(a);
    }

    public A get() {
        return a;
    }
}
