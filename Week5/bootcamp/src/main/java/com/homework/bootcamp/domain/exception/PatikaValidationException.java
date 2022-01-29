package com.homework.bootcamp.domain.exception;

import lombok.Getter;

@Getter
public class PatikaValidationException extends RuntimeException{
    private final ExceptionType exceptionType;
    private String detail;

    public PatikaValidationException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public PatikaValidationException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
