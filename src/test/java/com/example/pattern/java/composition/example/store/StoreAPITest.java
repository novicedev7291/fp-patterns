package com.example.pattern.java.composition.example.store;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class StoreAPITest {
    @Test
    void testStoreApiInteractionUsingFunctionComposition() {
        final List<Item> itemList = Arrays.asList(new Item("Shoe"), new Item("Shirt"), new Item("Trouser"));
        Function<List<Item>, Delivery> oneClickBuy = ((Function<List<Item>, Cart>)StoreAPI::buy)
                .andThen(StoreAPI::order)
                .andThen(StoreAPI::deliver);
        final Delivery delivery = oneClickBuy.apply(itemList);

        assertThat(delivery).isNotNull();
    }

}