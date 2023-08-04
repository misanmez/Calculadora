package com.calculadora.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionInfo {
	private String errorCode;
    private String message;

}
