package com.example.pattern.java.composition.example.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Function.identity;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class SalaryCalculator2 {
    private final List<Function<Double, Double>> fns;

    public SalaryCalculator2() {
        fns = new ArrayList<>();
    }

    public SalaryCalculator2 with(Function<Double, Double> rule) {
        fns.add(rule);
        return this;
    }

    public Double calculate(Double baseSalary) {
        return fns.stream()
                .reduce(identity(), Function::andThen)
                .apply(baseSalary);
    }
}
