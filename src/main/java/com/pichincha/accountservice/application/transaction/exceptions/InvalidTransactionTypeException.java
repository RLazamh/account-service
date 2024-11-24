package com.pichincha.accountservice.application.transaction.exceptions;

import com.pichincha.accountservice.application.common.exceptions.BaseException;

public class InvalidTransactionTypeException extends BaseException {
    public InvalidTransactionTypeException(String transactionType) {
        super("Invalid transaction type: " + transactionType, "INVALID_TRANSACTION_TYPE");
    }
}
