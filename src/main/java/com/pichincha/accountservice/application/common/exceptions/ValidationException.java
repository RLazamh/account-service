package com.pichincha.accountservice.application.common.exceptions;

public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}