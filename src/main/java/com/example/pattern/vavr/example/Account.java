package com.example.pattern.vavr.example;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

@Getter
@AllArgsConstructor(staticName = "of")
public class Account {
    private final AccountId id;
    private Money balance;

    public Either<TransferFailed, TransferSuccess> transferTo(Account other, Money amount) {
        if(canWithdraw(amount)) {
            balance = balance.minus(amount);
            other.balance = other.balance.add(amount);
            return announceSuccess();
        }
        return announceFailure();
    }

    private boolean canWithdraw(Money amount) {
        return amount.isLessThanEqualTo(balance);
    }

    private Either<TransferFailed, TransferSuccess> announceSuccess() {
        return right(TransferSuccess.newInstance());
    }

    private Either<TransferFailed, TransferSuccess> announceFailure() {
        return left(TransferFailed.newInstance());
    }
}
