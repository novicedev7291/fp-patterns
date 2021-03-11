package com.example.pattern.core;

import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface Try<A> {
    boolean isFailure();
    <B> Try<B> flatMap(Function<A, Try<B>> fn);
    <B> Try<B> map(Function<A, B> fn);
    A get();
}

