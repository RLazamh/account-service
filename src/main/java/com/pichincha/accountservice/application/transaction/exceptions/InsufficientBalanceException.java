package com.pichincha.accountservice.application.transaction.exceptions;

import com.pichincha.accountservice.application.common.exceptions.BaseException;

import java.math.BigDecimal;

public class InsufficientBalanceException extends BaseException {

    public InsufficientBalanceException(
            String accountNumber,
            BigDecimal currentBalance,
            BigDecimal transactionValue
    ) {
        super("Insufficient balance in account " + accountNumber +
                        ". Current balance: " + currentBalance +
                        ", attempted transaction value: " + transactionValue,
                "INSUFFICIENT_BALANCE"
        );
    }
}
