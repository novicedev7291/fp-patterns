package com.example.pattern.visitor.functional;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxCalculatorBuilder<R> extends BiConsumer<Class<?>, Function<Object, R>> {
    default void register(Class<?> type, Function<Object, R> function) {
        accept(type, function);
    }
}
