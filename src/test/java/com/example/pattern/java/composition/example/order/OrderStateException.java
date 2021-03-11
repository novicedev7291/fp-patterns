package com.example.pattern.java.composition.example.order;

public class OrderStateException extends RuntimeException {
    private static final long serialVersionUID = 3696202948233825615L;

    public OrderStateException(String message) {
        super(message);
    }
}
