package com.example.pattern.java.visitor.normal;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class NormalTaxCalculator implements TaxCalculator{
    public Double calculateOn(LiquorItem liquorItem) {
        return liquorItem.getPrice() * 0.18 + liquorItem.getPrice();
    }

    public Double calculateOn(TobaccoItem tobaccoItem) {
        return tobaccoItem.getPrice() * 0.32 + tobaccoItem.getPrice();
    }

    public Double calculateOn(NecessityItem necessityItem) {
        return necessityItem.getPrice() * 0 + necessityItem.getPrice();
    }
}
