package com.example.pattern.java.composition.example.order.v2;

import com.example.pattern.core.Failure;
import com.example.pattern.core.Money;
import com.example.pattern.core.Success;
import com.example.pattern.core.Try;
import com.example.pattern.java.composition.example.order.Customer;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.OrderId;
import com.example.pattern.java.composition.example.order.OrderState;
import com.example.pattern.java.composition.example.order.OrderStateException;
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

    public static Try<Order> allocate(Order order) {
        if(order.getState() != CONFIRMED)
            return new Failure<>(new OrderStateException("Only confirmed order can be allocated"));
        return Success.of(order.copy(ALLOCATED));
    }

    public static Try<Order> moveForShipping(Order order) {
        if(order.getState() != ALLOCATED)
            new Failure<>(new OrderStateException("Only allocated order can be moved to ready to ship"));
        return Success.of(order.copy(READY_TO_SHIP));
    }
}
