package com.example.pattern.java.composition.store;

import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class StoreAPI {
    public static Cart buy(List<Item> items) {
        return new Cart(items);
    }

    public static Order order(Cart cart) {
        return new Order(cart);
    }

    public static Delivery deliver(Order order) {
        return new Delivery(order);
    }
}
