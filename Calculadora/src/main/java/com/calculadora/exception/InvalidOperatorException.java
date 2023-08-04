package com.calculadora.exception;

@SuppressWarnings("serial")
public class InvalidOperatorException extends RuntimeException {
	private ExceptionInfo exceptionInfo;

    public InvalidOperatorException(String message) {
        super(message);
        this.exceptionInfo = new ExceptionInfo("INVALID_OPERATOR", message);
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
