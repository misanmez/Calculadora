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
        String errorMessage = "Error en la llamada al servicio";
        Exception ex = new HttpMessageNotReadableException(errorMessage,
                new MockHttpInputMessage(errorMessage.getBytes()));

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleMessageNotReadableException(ex);

        // Assert
        assertEquals(response.getErrorCode(), CalculadoraExcepciones.INVALID_PARAM);
        assertEquals("Error en los párametros de la request: " + errorMessage, response.getMessage());
        verify(tracer).trace("Error en los párametros de la request: " + errorMessage);
    }
    
    @Test
    public void testHandleInvalidOperandException() {
        // Arrange
        String errorMessage = "Operador inválido";
        InvalidOperatorException ex = new InvalidOperatorException(errorMessage);

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleInvalidOperandException(ex);

        // Assert
        assertEquals(response.getErrorCode(), CalculadoraExcepciones.INVALID_OPERATION);
        assertEquals("Operación no encontrada: " + errorMessage, response.getMessage());
        verify(tracer).trace("Operación no encontrada: " + errorMessage);
    }

    @Test
    public void testHandleInvalidParamException() {
        // Arrange
        String errorMessage = "Valor negativo no permitido";
        InvalidParameterException ex = new InvalidParameterException(errorMessage);

        // Act
        ExceptionInfo response = calculadoraExcepciones.handleInvalidParamException(ex);
        
        // Assert
        assertEquals(response.getErrorCode(), CalculadoraExcepciones.INVALID_PARAM);
        assertEquals("Parámetro incorrecto o inválido: " + errorMessage, response.getMessage());
        verify(tracer).trace("Parámetro incorrecto o inválido: " + errorMessage);
    }
}