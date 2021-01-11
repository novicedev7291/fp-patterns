package com.example.pattern.java.composition;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class Converter implements ExtendedBiFunction<Double, Double, Double>{
    public Double convert(Double rate, Double valueToConvert) {
        return rate * valueToConvert;
    }

    @Override
    public Double apply(Double rate, Double valueToConvert) {
        return rate * valueToConvert;
    }
}
