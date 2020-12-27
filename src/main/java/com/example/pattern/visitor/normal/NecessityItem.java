package com.example.pattern.visitor.normal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Getter
@RequiredArgsConstructor
class NecessityItem implements TaxableItem{
    private final Double price;

    public Double apply(TaxCalculator calculator) {
        return calculator.calculateOn(this);
    }
}
