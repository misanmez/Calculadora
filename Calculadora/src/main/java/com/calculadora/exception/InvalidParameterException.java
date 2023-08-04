package com.calculadora.exception;

@SuppressWarnings("serial")
public class InvalidParameterException extends RuntimeException {
    private ExceptionInfo exceptionInfo;

    public InvalidParameterException(String message) {
        super(message);
        this.exceptionInfo = new ExceptionInfo("INVALID_PARAMETER", message);
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}