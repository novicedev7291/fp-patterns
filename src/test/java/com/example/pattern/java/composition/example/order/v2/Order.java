package com.example.pattern.java.composition.example.order.v2;

import com.example.pattern.core.Money;
import com.example.pattern.java.composition.example.order.Customer;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.OrderId;
import com.example.pattern.java.composition.example.order.OrderState;
import com.example.pattern.java.composition.example.order.OrderStateException;

import java.util.List;

import static com.example.pattern.java.composition.example.order.OrderId.random;
import static com.example.pattern.java.composition.example.order.OrderState.ALLOCATED;
import static com.example.pattern.java.composition.example.order.OrderState.CONFIRMED;
import static com.example.pattern.java.composition.example.order.OrderState.READY_TO_SHIP;
import static java.math.BigDecimal.ZERO;

public class Order {
    private final OrderId id;
    private final OrderState state;
    private final Customer customer;
    private final List<Item> items;
    private final Money tax;
    private final Money total;

    public static com.example.pattern.java.composition.example.order.v1.Order with(Customer customer, List<Item> items) {
        Money tax = items.stream().map(Item::getTax).reduce(Money.of(ZERO), Money::add);
        Money total = items.stream().map(Item::getTotal).reduce(Money.of(ZERO), Money::add);
        return new com.example.pattern.java.composition.example.order.v1.Order(random(), CONFIRMED, customer, items, tax, total);
    }

    public com.example.pattern.java.composition.example.order.v1.Order copy(OrderState newState) {
        return new com.example.pattern.java.composition.example.order.v1.Order(id, newState, customer, items, tax, total);
    }

    public static com.example.pattern.java.composition.example.order.v1.Order allocate(com.example.pattern.java.composition.example.order.v1.Order order) {
        if(order.getState() != CONFIRMED)
            throw new OrderStateException("Only confirmed order can be allocated");
        return order.copy(ALLOCATED);
    }

    public static com.example.pattern.java.composition.example.order.v1.Order moveForShipping(com.example.pattern.java.composition.example.order.v1.Order order) {
        if(order.getState() != ALLOCATED)
            throw new OrderStateException("Only allocated order can be moved to ready to ship");
        return order.copy(READY_TO_SHIP);
    }
}
