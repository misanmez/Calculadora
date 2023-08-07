package com.calculadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

@Component
class OperacionRestaTest {

    @Mock
    private OperacionResta operacionResta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalcular() {
        // Arrange
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("5");
        when(operacionResta.calcular(a, b)).thenReturn(a.subtract(b));

        // Act
        BigDecimal resultado = operacionResta.calcular(a, b);

        // Assert
        assertEquals(a.subtract(b), resultado);
        verify(operacionResta).calcular(a, b);
    }

    @Test
    void testGetOperator() {
        // Arrange
        String operator = "restar";
        when(operacionResta.getOperator()).thenReturn(operator);

        // Act
        String resultOperator = operacionResta.getOperator();

        // Assert
        assertEquals(operator, resultOperator);
        verify(operacionResta).getOperator();
    }
}