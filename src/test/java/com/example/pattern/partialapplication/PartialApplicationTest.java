package com.example.pattern.partialapplication;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class PartialApplicationTest {
    @Test
    void test() {
        Sales sales = Sales.from("MAR").to("NOV");
        Double totalTax = sales.calculateTax(Percent.of(7.0));

        assertThat(totalTax).isEqualTo(842.1336);
    }
}
