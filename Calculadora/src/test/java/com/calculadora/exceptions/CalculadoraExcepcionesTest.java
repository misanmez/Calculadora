package com.calculadora.exceptions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;

import com.calculadora.exception.CalculadoraExcepciones;
import com.calculadora.exception.InvalidOperatorException;
import com.calculadora.exception.InvalidParameterException;

import io.corp.calculator.TracerImpl;

public class CalculadoraExcepcionesTest {

    private CalculadoraExcepciones calculadoraExcepciones;
    private TracerImpl tracer;

    @BeforeEach
    public void setUp() {
        tracer = mock(TracerImpl.class);
        calculadoraExcepciones = new CalculadoraExcepciones(tracer);
    }

    @Test
    public void testHandleMessageNotReadableException() {
        // Arrange
        String errorMessage = "Error en los párametros de la request";
        Exception ex = new HttpMessageNotReadableException(errorMessage,
                new MockHttpInputMessage(errorMessage.getBytes()));

        // Act
        ResponseEntity<String> response = calculadoraExcepciones.handleMessageNotReadableException(ex);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertTrue(response.getBody().toLowerCase().contains(errorMessage.toLowerCase()));
        verify(tracer).trace("Error en los párametros de la request: " + errorMessage);
    }
    
    @Test
    public void testHandleInvalidOperandException() {
        // Arrange
        String errorMessage = "Operación no encontrada: Operador inválido";
        InvalidOperatorException ex = new InvalidOperatorException("Operador inválido");

        // Act
        ResponseEntity<String> response = calculadoraExcepciones.handleInvalidOperandException(ex);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertEquals(errorMessage, response.getBody());
        verify(tracer).trace("Operación no encontrada: Operador inválido");
    }

    @Test
    public void testHandleInvalidParamException() {
        // Arrange
        String errorMessage = "Parámetro incorrecto o inválido: Valor negativo no permitido";
        InvalidParameterException ex = new InvalidParameterException("Valor negativo no permitido");

        // Act
        ResponseEntity<String> response = calculadoraExcepciones.handleInvalidParamException(ex);

        // Assert
        assertEquals(400, response.getStatusCode().value());
        assertEquals(errorMessage, response.getBody());
        verify(tracer).trace("Parámetro incorrecto o inválido: Valor negativo no permitido");
    }
}