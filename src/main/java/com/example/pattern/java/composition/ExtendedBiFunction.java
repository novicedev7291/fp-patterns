package com.example.pattern.java.composition;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface ExtendedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    default Function<U, R> curry(T t) {
        return u -> apply(t, u);
    }

    default <V> ExtendedBiFunction<T, V, R> compose(Function<? super V, ? extends U> before) {
        return (t, v) -> apply(t, before.apply(v));
    }
}
