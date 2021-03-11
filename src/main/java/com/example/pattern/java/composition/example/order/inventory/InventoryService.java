package com.example.pattern.java.composition.example.order.inventory;

import java.util.Map;

public interface InventoryService {
    boolean allocateItems(Map<String, Integer> skuQtyMap);
}
