package com.example.pattern.java.visitor.normal;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxCalculator {
    Double calculateOn(LiquorItem liquorItem);
    Double calculateOn(TobaccoItem tobaccoItem);
    Double calculateOn(NecessityItem necessityItem);
}
