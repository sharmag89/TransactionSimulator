package com.material.consumer.exceptions;

import ch.qos.logback.core.spi.ErrorCodes;

public class ServiceException extends RuntimeException{
    private final String description;
    private final ErrorCode errorCode;

    public ServiceException(String description, ErrorCode errorCode) {
        super(description);
        this.description = description;
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String description, Throwable inCause) {
        super(inCause.getMessage(), inCause);
        this.errorCode = errorCode;
        this.description = description;
    }
}
