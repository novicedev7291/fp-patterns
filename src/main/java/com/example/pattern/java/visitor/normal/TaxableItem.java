package com.example.pattern.java.visitor.normal;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxableItem {
    Double apply(TaxCalculator calculator);
}
