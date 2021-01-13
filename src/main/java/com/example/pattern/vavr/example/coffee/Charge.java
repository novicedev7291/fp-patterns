package com.example.pattern.vavr.example.coffee;

import lombok.Value;

@Value
class Charge {
    CreditCard cc;
    Double price;

    public Charge add(Charge other) {
        if(cc == other.cc) return new Charge(cc, price + other.price);
        throw new IllegalArgumentException("Cannot create charge, invalid credit card");
    }
}
