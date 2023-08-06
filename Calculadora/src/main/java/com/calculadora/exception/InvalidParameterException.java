package com.calculadora.exception;

@SuppressWarnings("serial")
public class InvalidParameterException extends Exception {
	public InvalidParameterException(String message) {
		super(message);
	}
}