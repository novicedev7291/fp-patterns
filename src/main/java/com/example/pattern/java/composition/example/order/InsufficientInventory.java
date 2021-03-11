package com.example.pattern.java.composition.example.order;

public class InsufficientInventory extends RuntimeException {
    private static final long serialVersionUID = 2248648049339950056L;

    public InsufficientInventory(String message) {
        super(message);
    }
}
