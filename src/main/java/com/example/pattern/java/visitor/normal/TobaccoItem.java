package com.example.pattern.java.visitor.normal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Getter
@RequiredArgsConstructor
class TobaccoItem implements TaxableItem {
    private final Double price;

    public Double apply(TaxCalculator calculator) {
        return calculator.calculateOn(this);
    }
}
