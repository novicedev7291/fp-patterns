package com.example.pattern.java.composition.example.salary;

import java.util.function.Function;

/**
 * A little better version of {@link SalaryCalculator2}, because
 * it is immutable now and thread safe.
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class SalaryCalculator3 {
    private final Function<Double, Double> fn;

    public SalaryCalculator3() {
        this(Function.identity());
    }

    private SalaryCalculator3(Function<Double, Double> fn) {
        this.fn = fn;
    }

    public SalaryCalculator3 with(Function<Double, Double> fn) {
        return new SalaryCalculator3(this.fn.andThen(fn));
    }

    public Double compute(Double base) {
        return this.fn.apply(base);
    }
}
