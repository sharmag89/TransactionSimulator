package com.material.consumer.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {
    SERVICE_NOT_REACHABLE(SERVICE_UNAVAILABLE),
    INVALID(BAD_REQUEST),
    NOT_PRESENT(NOT_FOUND),
    GENERAL_ERROR(INTERNAL_SERVER_ERROR);


    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.getHttpStatus();
    }
}
