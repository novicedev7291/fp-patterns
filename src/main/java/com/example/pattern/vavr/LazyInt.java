package com.example.pattern.vavr;

import io.vavr.Lazy;

import java.util.function.Supplier;

class LazyInt {
    private final Lazy<Integer> lazy;

    public LazyInt(Supplier<Integer> initializer) {
        lazy = Lazy.of(initializer);
    }

    public Integer getValue() {
        return lazy.get();
    }
}
