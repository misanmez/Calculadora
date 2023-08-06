package com.calculadora.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.calculadora.dto.ResultResponse;
import com.calculadora.exception.*;
import com.calculadora.model.OperacionRequest;
import com.calculadora.service.CalculadoraService;

public class CalculadoraControllerTest {

	private CalculadoraService calculadoraService;
    private CalculadoraController calculadoraController;

    @BeforeEach
    public void setUp() {
        calculadoraService = mock(CalculadoraService.class);
        calculadoraController = new CalculadoraController(calculadoraService);
    }

    @Test
    public void testCalculate_Addition_Success() throws InvalidOperatorException, InvalidParameterException {
        // Arrange
        String operator = "sumar";
        BigDecimal operand1 = new BigDecimal("10");
        BigDecimal operand2 = new BigDecimal("5");
        OperacionRequest request = new OperacionRequest(operand1, operand2);
        BigDecimal expectedResult = new BigDecimal("15");
        ResultResponse resultResponse = new ResultResponse(expectedResult);
        when(calculadoraService.performOperation(operator, request)).thenReturn(resultResponse);

        // Act
        ResponseEntity<?> responseEntity = calculadoraController.calculate(operator, request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ResultResponse);
        ResultResponse response = (ResultResponse) responseEntity.getBody();
        assertEquals(expectedResult, response.getResult());
        verify(calculadoraService).performOperation(operator, request);
    }

    @Test
    public void testCalculate_InvalidOperator_Exception() throws InvalidOperatorException, InvalidParameterException {
        // Arrange
        String operator = "otro"; // Invalid operator
        OperacionRequest request = new OperacionRequest(new BigDecimal("10"), new BigDecimal("5"));
        when(calculadoraService.performOperation(operator, request))
            .thenThrow(new InvalidOperatorException("Operador no válido: otro"));

        // Act and Assert
        Exception exception = assertThrows(InvalidOperatorException.class, () -> extracted(operator, request));

        assertEquals("Operador no válido: otro", exception.getMessage());
        verify(calculadoraService).performOperation(operator, request);
    }

    @Test
    public void testCalculate_InvalidParameter_Exception() throws InvalidOperatorException, InvalidParameterException {
        // Arrange
        String operator = "sumar";
        OperacionRequest request = new OperacionRequest(new BigDecimal("10"), null); // Invalid parameter
        when(calculadoraService.performOperation(operator, request))
            .thenThrow(new InvalidParameterException("Ambos operandos deben ser proporcionados"));

        // Act and Assert
        Exception exception = assertThrows(InvalidParameterException.class, () -> extracted(operator, request));

        assertEquals("Ambos operandos deben ser proporcionados", exception.getMessage());
        verify(calculadoraService).performOperation(operator, request);
    }

	private void extracted(String operator, OperacionRequest request) throws InvalidOperatorException, InvalidParameterException {
		calculadoraController.calculate(operator, request);
	}
}
