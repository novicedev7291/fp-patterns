package com.example.pattern.java.composition.example.order;

import com.example.pattern.core.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.pattern.java.composition.example.order.OrderId.random;
import static com.example.pattern.java.composition.example.order.OrderState.ALLOCATED;
import static com.example.pattern.java.composition.example.order.OrderState.CONFIRMED;
import static com.example.pattern.java.composition.example.order.OrderState.READY_TO_SHIP;
import static java.math.BigDecimal.ZERO;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private final OrderId id;
    private final OrderState state;
    private final Customer customer;
    private final List<Item> items;
    private final Money tax;
    private final Money total;

    public static Order with(Customer customer, List<Item> items) {
        Money tax = items.stream().map(Item::getTax).reduce(Money.of(ZERO), Money::add);
        Money total = items.stream().map(Item::getTotal).reduce(Money.of(ZERO), Money::add);
        return new Order(random(), CONFIRMED, customer, items, tax, total);
    }

    public Order copy(OrderState newState) {
        return new Order(id, newState, customer, items, tax, total);
    }

    public static Order allocate(Order order) {
        if(order.getState() != CONFIRMED)
            throw new OrderStateException("Only confirmed order can be allocated");
        return order.copy(ALLOCATED);
    }

    public static Order moveForShipping(Order order) {
        if(order.getState() != ALLOCATED)
            throw new OrderStateException("Only allocated order can be moved to ready to ship");
        return order.copy(READY_TO_SHIP);
    }
}

