package com.example.pattern.vavr.example.coffee;

import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;

import java.util.List;

class CafeV2 {
    public Tuple2<Coffee, Charge> buyCoffee(CreditCard cc) {
        Coffee cup = new Coffee();
        return new Tuple2<>(cup, new Charge(cc, cup.getPrice())); // Remove cc.charge side effect, instead create charge object
    }

    public Tuple2<List<Coffee>, Charge> buyCoffees(CreditCard cc, int n) {
        final Tuple2<Stream<Coffee>, Stream<Charge>> purchases = Stream.continually(() -> buyCoffee(cc))
                                                                   .subSequence(0, n)
                                                                   .unzip(Function1.identity());
        return new Tuple2<>( // Produces a tuple of all coffees and single charge accumulated on credit card
                purchases._1.toJavaList(),
                purchases._2.foldLeft(new Charge(cc, 0d), Charge::add)
        );
    }
}
