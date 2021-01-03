package com.example.pattern.vavr.example.either;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void shouldFailToTransferMoneyWhenBalanceNotEnough() {
        Account account1 = AccountFactory.create(UUID.randomUUID(), Money.of(1000L));

        Account account2 = AccountFactory.create(UUID.randomUUID(), Money.of(500L));

        Either<TransferFailed, TransferSuccess> result = account1.transferTo(account2, Money.of(1200L));

        assertThat(result.isLeft()).isTrue();
    }

    @Test
    void shouldTransferMoneyWhenBalanceEnough() {
        Account account1 = AccountFactory.create(UUID.randomUUID(), Money.of(1000L));

        Account account2 = AccountFactory.create(UUID.randomUUID(), Money.of(500L));

        Either<TransferFailed, TransferSuccess> result = account1.transferTo(account2, Money.of(700L));

        assertThat(result.isRight()).isTrue();
        assertThat(account1.getBalance()).isEqualTo(Money.of(300L));
        assertThat(account2.getBalance()).isEqualTo(Money.of(1200L));
    }
}
