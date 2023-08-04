package com.calculadora.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ResultResponseTest {

	@Test
    public void testResultResponse_GetAndSet() {
        // Arrange
        BigDecimal result = new BigDecimal("15");
        ResultResponse response = new ResultResponse();

        // Act
        response.setResult(result);

        // Assert
        assertEquals(result, response.getResult());
    }
}
