package com.calculadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

@Component
class OperacionSumaTest {

    @Mock
    private OperacionSuma operacionSuma;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalcular() {
        // Arrange
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("5");
        when(operacionSuma.calcular(a, b)).thenReturn(a.add(b));

        // Act
        BigDecimal resultado = operacionSuma.calcular(a, b);

        // Assert
        assertEquals(a.add(b), resultado);
        verify(operacionSuma).calcular(a, b);
    }

    @Test
    void testGetOperator() {
        // Arrange
        String operator = "sumar";
        when(operacionSuma.getOperator()).thenReturn(operator);

        // Act
        String resultOperator = operacionSuma.getOperator();

        // Assert
        assertEquals(operator, resultOperator);
        verify(operacionSuma).getOperator();
    }
}