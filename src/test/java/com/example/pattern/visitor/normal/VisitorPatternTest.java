package com.example.pattern.visitor.normal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class VisitorPatternTest {
    @Test
    void should_calculate_different_tax_for_all_items() {
        //given
        LiquorItem liquorItem = new LiquorItem(1800.0);
        TobaccoItem tobaccoItem = new TobaccoItem(18.75);
        NecessityItem necessityItem = new NecessityItem(22.35);

        NormalTaxCalculator taxCalculator = new NormalTaxCalculator();

        assertThat(liquorItem.apply(taxCalculator)).isEqualTo(2124.0);
        assertThat(tobaccoItem.apply(taxCalculator)).isEqualTo(24.75);
        assertThat(necessityItem.apply(taxCalculator)).isEqualTo(22.35);

    }
}
