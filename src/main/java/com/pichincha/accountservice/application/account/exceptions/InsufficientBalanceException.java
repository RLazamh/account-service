package com.pichincha.accountservice.application.account.exceptions;

import com.pichincha.accountservice.application.common.exceptions.BaseException;

public class InsufficientBalanceException extends BaseException {

    public InsufficientBalanceException(
            String accountNumber,
            double currentBalance,
            double transactionValue
    ) {
        super("Insufficient balance in account " + accountNumber +
                        ". Current balance: " + currentBalance +
                        ", attempted transaction value: " + transactionValue,
                "INSUFFICIENT_BALANCE"
        );
    }
}
