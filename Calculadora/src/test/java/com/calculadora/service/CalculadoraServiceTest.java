package com.calculadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.calculadora.dto.ResultResponse;
import com.calculadora.exception.InvalidOperatorException;
import com.calculadora.exception.InvalidParameterException;
import com.calculadora.model.OperacionRequest;

import io.corp.calculator.TracerImpl;

class CalculadoraServiceTest {

	private CalculadoraService calculadoraService;
    private TracerImpl tracer;

    @BeforeEach
    void setUp() {
        tracer = mock(TracerImpl.class);
        BinaryOperation additionOperation = mock(BinaryOperation.class);
        BinaryOperation subtractionOperation = mock(BinaryOperation.class);

        when(additionOperation.getOperator()).thenReturn("sumar");
        when(subtractionOperation.getOperator()).thenReturn("restar");

        when(additionOperation.calcular(any(), any())).thenReturn(new BigDecimal("15"));
        when(subtractionOperation.calcular(any(), any())).thenReturn(new BigDecimal("5"));

        BinaryOperation[] binaryOperations = { additionOperation, subtractionOperation };
        calculadoraService = new CalculadoraService(binaryOperations, tracer);
    }

    @Test
    void testPerformOperation_Addition() throws InvalidParameterException, InvalidOperatorException {
        // Arrange
        String operator = "sumar";
        OperacionRequest request = new OperacionRequest(new BigDecimal("10"), new BigDecimal("5"));

        // Act
        ResultResponse result = calculadoraService.performOperation(operator, request);

        // Assert
        assertEquals(new BigDecimal("15"), result.getResult());
    }

    @Test
    void testPerformOperation_Subtraction() throws InvalidParameterException, InvalidOperatorException {
        // Arrange
        String operator = "restar";
        OperacionRequest request = new OperacionRequest(new BigDecimal("10"), new BigDecimal("5"));

        // Act
        ResultResponse result = calculadoraService.performOperation(operator, request);

        // Assert
        assertEquals(new BigDecimal("5"), result.getResult());
    }

    @Test
    void testPerformOperation_InvalidOperator_Exception() {
        // Arrange
        String operator = "*"; // Invalid operator
        OperacionRequest request = new OperacionRequest(new BigDecimal("10"), new BigDecimal("5"));

        // Act and Assert
        Exception exception = assertThrows(InvalidOperatorException.class, () -> {
            calculadoraService.performOperation(operator, request);
        });

        assertEquals("*", exception.getMessage());
    }
    
    @Test
    void testPerformOperation_InvalidParameter_Exception() {
        // Arrange
        String operator = "sumar";
        OperacionRequest request = new OperacionRequest(null, new BigDecimal("5")); // Invalid operand1

        // Act and Assert
        Exception exception = assertThrows(InvalidParameterException.class, () -> {
            calculadoraService.performOperation(operator, request);
        });

        assertEquals("Ambos operandos deben ser proporcionados", exception.getMessage());
    }
}
