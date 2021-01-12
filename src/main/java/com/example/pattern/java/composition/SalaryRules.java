package com.example.pattern.java.composition;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@NoArgsConstructor(access = PRIVATE)
class SalaryRules {
    static double allowance(double salary) {
        return salary * 1.2;
    }

    static double bonus(double salary) {
        return salary * 1.1;
    }

    static double tax(double salary) {
        return salary * 0.9;
    }

    static double telephoneAllowance(double salary) {
        return salary * 1.02;
    }
}
