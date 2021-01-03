package com.example.pattern.java.visitor.functional;

import java.util.function.Consumer;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxCalculatorInitializer<R> extends Consumer<TaxCalculatorBuilder<R>> {
    default void init(TaxCalculatorBuilder<R> builder) {
        accept(builder);
    }
}
