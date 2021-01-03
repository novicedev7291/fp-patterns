package com.example.pattern.vavr;

import io.vavr.control.Try;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValuesTest {
    @Test
    void checkHowTryWorks() {
        Integer result = Try.of(() -> Integer.parseInt("#991")).getOrElse(0);
        assertThat(result).isZero();
    }

    @Test
    void shouldLazyInitValue() {
        final MagicIntProvider provider = new MagicIntProvider();
        LazyInt aLazyInt = new LazyInt(provider::get);

        aLazyInt.getValue();
        aLazyInt.getValue();
        aLazyInt.getValue();

        assertThat(provider.count).isEqualTo(1);
    }

    @Getter
    static class MagicIntProvider {
        private int count;

        public int get() {
            count++;
            return (int) Math.random();
        }
    }

}
