package com.example.pattern.vavr.example.either;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class TransferFailed {
    private final UUID id;

    public static TransferFailed newInstance() {
        return new TransferFailed(UUID.randomUUID());
    }
}
