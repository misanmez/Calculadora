package com.calculadora.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;

import com.calculadora.exception.CalculadoraExcepciones;
import com.calculadora.exception.ExceptionInfo;
import com.calculadora.exception.InvalidOperatorException;
import com.calculadora.exception.InvalidParameterException;

import io.corp.calculator.TracerImpl;

class CalculadoraExcepcionesTest {

    private CalculadoraExcepciones calculadoraExcepciones;
    private TracerImpl tracer;

    @BeforeEach
    void setUp() {
        tracer = mock(TracerImpl.class);
        calculadoraExcepciones = new CalculadoraExcepciones(tracer);
    }

    @Test
    void testHandleMessageNotReadableException() {
        // Arrange
        String errorMessage = "Error en la llamada al servicio";
        Exception ex = new HttpMessageNotReadableException(errorMessage,
                new MockHttpInputMessage(errorMessage.getBytes()));

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleMessageNotReadableException(ex);

        // Assert
        assertEquals(CalculadoraExcepciones.INVALID_PARAM, response.getErrorCode());
        assertEquals("Error en los párametros de la request: " + errorMessage, response.getMessage());
        verify(tracer).trace("Error en los párametros de la request: " + errorMessage);
    }

    @Test
    void testHandleInvalidOperandException() {
        // Arrange
        String errorMessage = "Operador inválido";
        InvalidOperatorException ex = new InvalidOperatorException(errorMessage);

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleInvalidOperandException(ex);

        // Assert
        assertEquals(CalculadoraExcepciones.INVALID_OPERATION, response.getErrorCode());
        assertEquals("Operación no encontrada: " + errorMessage, response.getMessage());
        verify(tracer).trace("Operación no encontrada: " + errorMessage);
    }

    @Test
    void testHandleInvalidParamException() {
        // Arrange
        String errorMessage = "Valor negativo no permitido";
        InvalidParameterException ex = new InvalidParameterException(errorMessage);

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleInvalidParamException(ex);
        
        // Assert
        assertEquals(CalculadoraExcepciones.INVALID_PARAM, response.getErrorCode());
        assertEquals("Parámetro incorrecto o inválido: " + errorMessage, response.getMessage());
        verify(tracer).trace("Parámetro incorrecto o inválido: " + errorMessage);
    }
}