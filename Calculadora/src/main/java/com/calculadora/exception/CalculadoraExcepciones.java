package com.calculadora.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.corp.calculator.TracerAPI;

@RestControllerAdvice
public class CalculadoraExcepciones {
    
	private final TracerAPI tracer;

    @Autowired
    public CalculadoraExcepciones(TracerAPI tracer) {
        this.tracer = tracer;
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMessageNotReadableException(Exception ex) {
      tracer.trace("Error en los párametros de la request: " + ex.getMessage());
    	
      return ResponseEntity.badRequest().body("Error en los párametros de la request: " + ex.getMessage());
    }
    
    @ExceptionHandler(InvalidOperatorException.class)
    public ResponseEntity<String> handleInvalidOperandException(InvalidOperatorException ex) {
    	tracer.trace("Operación no encontrada: " + ex.getMessage());
    	
    	return ResponseEntity.badRequest().body("Operación no encontrada: " + ex.getMessage());
    }
}
