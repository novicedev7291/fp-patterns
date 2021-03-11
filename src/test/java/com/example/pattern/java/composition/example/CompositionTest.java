package com.example.pattern.java.composition.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
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

    @Test
    void shouldProduceEachEmployeeSalary() {
        List<Employee> employees = new ArrayList<>();

        Map<Integer, Double> salaryMap = new HashMap<>();

        for(Employee employee : employees) {
            salaryMap.put(employee.getId(), employee.calculateSalary());
        }

        salaryMap = employees.stream()
                            .collect(toMap(Employee::getId, Employee::calculateSalary));
    }

    @Getter
    @RequiredArgsConstructor
    class Employee {
        private final Integer id;
        private final String name;
        private final int age;
        private final Double basic;

        // constructor and getters omitted for brevity
        public Double calculateSalary() {
            Double basicPlusHra = basic + basic * 0.10;
            return  basicPlusHra - basicPlusHra * 0.30;
        }
    }
}
