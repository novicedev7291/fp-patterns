package com.example.pattern.vavr.example.coffee;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class CafeV1 {
    public Coffee buyCoffee(CreditCard cc) {
        Coffee cup = new Coffee();
        cc.charge(cup.getPrice()); // Problem 1: cc.charge(cup.getPrice()) is a side effect function, hard to test one
        return cup;
    }

    public List<Coffee> buyCoffees(CreditCard cc, int noOfCoffees) {
        return Stream.generate(() -> buyCoffee(cc)) // Problem 2: buyCoffee call charges credit card every time
                .limit( noOfCoffees )
                .collect( toList() );
    }
}
