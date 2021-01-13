package com.example.pattern.java.composition;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryCalculatorTest {
    @Test
    void shouldCalculateNetSalaryGivenAllowanceAndTelephoneAllowance() {
        SalaryCalculator calculator = new SalaryCalculator();
        //We can have builder instead of boolean vars, but that won't help much
        //because, we will need to change the builder class again to add new rule or flag
        //and then SalaryCalculator finally to use the rule or flag
        final Double netSalary = calculator.calculate(576d, true, false, true, true);

        assertThat(netSalary).isCloseTo(634.522, Percentage.withPercentage(0.001));
    }

    @Test
    void shouldCalculateNetSalaryGivenAllowanceBonusTelephoneAllowance() {
        final Double netSalary = new SalaryCalculator2()
                .with(SalaryRules::allowance)
                .with(SalaryRules::bonus)
                .with(SalaryRules::telephoneAllowance)
                .with(SalaryRules::tax)
                .calculate(576d);
        assertThat(netSalary).isCloseTo(697.973, Percentage.withPercentage(0.001));
    }

    @Test
    void shouldCalculateNetSalaryGivenAllowanceBonusTaxWithVersion3() {
        final Double netSalary = new SalaryCalculator3()
                .with(SalaryRules::allowance)
                .with(SalaryRules::bonus)
                .with(SalaryRules::telephoneAllowance)
                .with(SalaryRules::tax)
                .compute(576d);
        assertThat(netSalary).isCloseTo(697.973, Percentage.withPercentage(0.001));
    }

}