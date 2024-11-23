package com.pichincha.accountservice.application.common.exceptions;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String code;

    public BaseException(String message, String code) {
        super(message);
        this.code = code;
    }

}
