package com.example.pattern.java.composition.example;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class CompositionTest {
    @Test
    void shouldConvertCelsiusToFahrenheit() {
        //F = C * 9/5 + 32
        Converter converter = new Converter();
        final Function<Double, Double> c2f = converter.curry(9.0 / 5).andThen(n -> n + 32);

        assertThat(c2f.apply(32.5)).isCloseTo(90.5, Percentage.withPercentage(0.001));
    }

    @Test
    void shouldConvertFahrenheitToCelsius() {
        // C = (F - 32) * 5/9
        Converter converter = new Converter();
        Function<Double, Double> f2c = converter.compose((Double n) -> n - 32)
                                                .curry(5.0 / 9);
        assertThat(f2c.apply(90.5)).isCloseTo(32.5, Percentage.withPercentage(0.001));
    }

    @Test
    void shouldConvertMilesToKms() {
        Converter converter = new Converter();
        Double kms = converter.convert(1.60934d, 20d);

        assertThat(kms).isCloseTo(32.1869d, Percentage.withPercentage(0.001));

        final Function<Double, Double> mi2KmsConverter = converter.curry(1.60934d);
        assertThat(mi2KmsConverter.apply(20d)).isCloseTo(32.1869d, Percentage.withPercentage(0.001));
    }
}
