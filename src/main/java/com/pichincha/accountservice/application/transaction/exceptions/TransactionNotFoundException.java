package com.pichincha.accountservice.application.transaction.exceptions;

import com.pichincha.accountservice.application.common.exceptions.BaseException;

public class TransactionNotFoundException extends BaseException {

    public TransactionNotFoundException(Long id) {
        super("Transaction with ID " + id + " not found.", "TRANSACTION_NOT_FOUND");
    }
}
