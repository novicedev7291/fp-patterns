package com.example.pattern.visitor.functional;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class VisitorPatternTest {
    @Test
    void should_calculate_tax_on_all_items() {
        TaxCalculatorInitializer<Double> taxCalculatorInitializer =
                TaxCalculator.<LiquorItem, Double>forType(LiquorItem.class).execute((LiquorItem item) -> item.getPrice() * 0.18 + item.getPrice())
                             .forType(TobaccoItem.class).execute((TobaccoItem item) -> item.getPrice() * 0.32 + item.getPrice())
                             .forType(NecessityItem.class).execute((NecessityItem item) -> item.getPrice() * 0.0 + item.getPrice());

        TaxCalculator<Double> taxCalculator = TaxCalculator.of(taxCalculatorInitializer);

        LiquorItem liquorItem = new LiquorItem(1765.0);
        TaxableItem<LiquorItem> taxableLiquorItem = TaxableItem.makeTaxableItem(liquorItem);
        final Double liquorFinalPrice = taxableLiquorItem.apply(taxCalculator);

        TobaccoItem tobaccoItem = new TobaccoItem(18.95);
        TaxableItem<TobaccoItem> taxableTobaccoItem  = TaxableItem.makeTaxableItem(tobaccoItem);
        final Double tobaccoFinalPrice = taxableTobaccoItem.apply(taxCalculator);

        NecessityItem necessityItem = new NecessityItem(22.13);
        TaxableItem<NecessityItem> taxableNecessityItem = TaxableItem.makeTaxableItem(necessityItem);
        final Double necessityFinalPrice = taxableNecessityItem.apply(taxCalculator);

        assertThat(liquorFinalPrice).isEqualTo(2082.7);
        assertThat(tobaccoFinalPrice).isEqualTo(25.014);
        assertThat(necessityFinalPrice).isEqualTo(22.13);
    }
}
