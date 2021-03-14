package com.example.pattern.java.composition.example.order.v3;

import com.example.pattern.core.Failure;
import com.example.pattern.core.Money;
import com.example.pattern.core.Try;
import com.example.pattern.core.TryReader;
import com.example.pattern.java.composition.example.order.Address;
import com.example.pattern.java.composition.example.order.City;
import com.example.pattern.java.composition.example.order.Country;
import com.example.pattern.java.composition.example.order.Customer;
import com.example.pattern.java.composition.example.order.InsufficientInventory;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.Name;
import com.example.pattern.java.composition.example.order.OrderState;
import com.example.pattern.java.composition.example.order.Quantity;
import com.example.pattern.java.composition.example.order.State;
import com.example.pattern.java.composition.example.order.Street;
import com.example.pattern.java.composition.example.order.inventory.InventoryService;
import com.example.pattern.java.composition.example.order.v2.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.example.pattern.java.composition.example.order.v3.OrderService.allocate;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    @Test
    void shouldMoveConfirmedOrderToReadyToShip() {
        //Given
        Address address = Address.of(
                Street.of("Dummy street 1"),
                Street.of("street 2"),
                City.of("Gurugram"),
                State.of("Haryana"),
                Country.of("India")
        );
        Customer customer = Customer.of(
                Name.of("Kuldeep", "Yadav"),
                address
        );
        List<Item> items = asList(
                Item.of("SKU1", Quantity.of(1), Money.of(1.2d), Money.of(0.2d), Money.of(1.2d)),
                Item.of("SKU2", Quantity.of(2), Money.of(1.2d), Money.of(0.4d), Money.of(2.4d))
        );
        Order order = Order.with(customer, items);

        //When
        TryReader<InventoryService, Order> tryReader =
                allocate(order).flatMap(OrderService::pack);

        Try<Order> possiblyShipped = tryReader.apply(new MockInventoryService())
                                              .flatMap(OrderService::moveForShipping);

        assertThat(possiblyShipped.isFailure()).isFalse();
        assertThat(possiblyShipped.get().getState()).isEqualTo(OrderState.READY_TO_SHIP);
    }

    @Test
    void shouldFailToShipWhenOrderFailedToPack() {
        //Given
        Address address = Address.of(
                Street.of("Dummy street 1"),
                Street.of("street 2"),
                City.of("Gurugram"),
                State.of("Haryana"),
                Country.of("India")
        );
        Customer customer = Customer.of(
                Name.of("Kuldeep", "Yadav"),
                address
        );
        List<Item> items = asList(
                Item.of("SKU1", Quantity.of(1), Money.of(1.2d), Money.of(0.2d), Money.of(1.2d)),
                Item.of("SKU2", Quantity.of(2), Money.of(1.2d), Money.of(0.4d), Money.of(2.4d))
        );
        Order order = Order.with(customer, items);

        //When
        TryReader<InventoryService, Order> tryReader =
                allocate(order).flatMap(OrderService::pack);

        Try<Order> possiblyShipped = tryReader.apply(new MockFailureInventoryService())
                                              .flatMap(OrderService::moveForShipping);

        assertThat(possiblyShipped.isFailure()).isTrue();
        Object error = ((Failure<Order>) possiblyShipped).getError();
        assertThat(error).isNotNull();

        InsufficientInventory ex = (InsufficientInventory) error;
        assertThat(ex.getMessage()).isNotNull();
    }

    static class MockInventoryService implements InventoryService {

        @Override
        public boolean allocateItems(Map<String, Integer> skuQtyMap) {
            return true;
        }

        @Override
        public boolean packItems(Map<String, Integer> skuQtyMap) {
            return true;
        }
    }

    static class MockFailureInventoryService implements InventoryService {

        @Override
        public boolean allocateItems(Map<String, Integer> skuQtyMap) {
            return false;
        }

        @Override
        public boolean packItems(Map<String, Integer> skuQtyMap) {
            return false;
        }
    }
}