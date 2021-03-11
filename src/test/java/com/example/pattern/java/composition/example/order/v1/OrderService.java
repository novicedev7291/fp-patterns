package com.example.pattern.java.composition.example.order.v1;

import com.example.pattern.java.composition.example.order.Customer;
import com.example.pattern.java.composition.example.order.Delivery;
import com.example.pattern.java.composition.example.order.InsufficientInventory;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.Order;
import com.example.pattern.java.composition.example.order.inventory.InventoryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.example.pattern.java.composition.example.order.Order.allocate;
import static com.example.pattern.java.composition.example.order.Order.moveForShipping;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
class OrderService {
    private final InventoryService inventoryService;

    public Order placeOrder(Customer customer, List<Item> items) {
        return Order.with(customer, items);
    }

    public Delivery processForDelivery(Order order) {
        Map<String, Integer> skuQtyMap = order.getItems()
                                              .stream()
                                              .collect(
                                                      toMap(Item::getCode,item -> item.getQty().getValue())
                                              );
        if(!inventoryService.allocateItems(skuQtyMap))
            throw new InsufficientInventory("Inventory is not sufficient for the order");
        Order allocatedOrder = allocate(order);
        Order readyToShipOrder = moveForShipping(allocatedOrder);
        return Delivery.of(readyToShipOrder);
    }
}
