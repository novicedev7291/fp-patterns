package com.example.pattern.visitor.normal;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxableItem {
    Double apply(TaxCalculator calculator);
}
