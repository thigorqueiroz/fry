package com.thigorqueiroz.fry.domain.model.common;

public class BusinessException extends RuntimeException{
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }
}
