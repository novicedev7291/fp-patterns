package com.example.pattern.vavr.example.either;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public class TransferSuccess {
    private final UUID id;

    public static TransferSuccess newInstance() {
        return new TransferSuccess(UUID.randomUUID());
    }
}
