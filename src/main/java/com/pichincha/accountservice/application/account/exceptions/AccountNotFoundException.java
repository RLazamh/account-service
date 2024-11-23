package com.pichincha.accountservice.application.account.exceptions;

import com.pichincha.accountservice.application.common.exceptions.BaseException;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(Long id) {
        super("Account with ID " + id + " not found.", "ACCOUNT_NOT_FOUND");
    }
}
