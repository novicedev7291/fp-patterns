package com.example.pattern.java.composition;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class SalaryCalculator {
    private double addAllowance(Double salary) {
        return salary * 1.2;
    }

    private double addBonus(Double salary) {
        return salary * 1.1;
    }

    private double addTax(Double salary) {
        return salary * 0.9;
    }

    private double addTelephoneAllowance(Double salary) {
        return salary * 1.02;
    }

    public Double calculate(Double baseSalary, boolean...bs) {
        double salary = baseSalary;
        if(bs[0]) salary = addAllowance(salary);
        if(bs[1]) salary = addBonus(salary);
        if(bs[2]) salary = addTax(salary);
        if(bs[3]) salary = addTelephoneAllowance(salary);
        return salary;
    }
}
