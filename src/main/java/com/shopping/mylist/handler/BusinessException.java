package com.shopping.mylist.handler;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RuntimeException getUserNotFound() {
        return null;
    }
}
