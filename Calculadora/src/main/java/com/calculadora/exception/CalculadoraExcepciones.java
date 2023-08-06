package com.calculadora.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.corp.calculator.TracerImpl;

@RestControllerAdvice
public class CalculadoraExcepciones {

	public static final String INVALID_PARAM = "INVALID_PARAM";
	public static final String INVALID_OPERATION = "INVALID_OPERATION";

	private final TracerImpl tracer;

	@Autowired
	public CalculadoraExcepciones(TracerImpl tracer) {
		this.tracer = tracer;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleMessageNotReadableException(Exception ex) {
		tracer.trace("Error en los párametros de la request: " + ex.getMessage());
		return new ExceptionInfo(INVALID_PARAM, "Error en los párametros de la request: " + ex.getMessage());
	}

	@ExceptionHandler(InvalidOperatorException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleInvalidOperandException(InvalidOperatorException ex) {
		tracer.trace("Operación no encontrada: " + ex.getMessage());
		return new ExceptionInfo(INVALID_OPERATION, "Operación no encontrada: " + ex.getMessage());
	}

	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionInfo handleInvalidParamException(InvalidParameterException ex) {
		tracer.trace("Parámetro incorrecto o inválido: " + ex.getMessage());
		return new ExceptionInfo(INVALID_PARAM, "Parámetro incorrecto o inválido: " + ex.getMessage());
	}
}
