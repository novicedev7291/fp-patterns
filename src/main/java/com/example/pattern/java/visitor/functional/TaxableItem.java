package com.example.pattern.java.visitor.functional;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxableItem<T> {
    Double apply(TaxCalculator<Double> taxCalculator);

    static <T> TaxableItem<T> makeTaxableItem(T t) {
        return taxCalculator -> taxCalculator.calculateOn(t);
    }
}
