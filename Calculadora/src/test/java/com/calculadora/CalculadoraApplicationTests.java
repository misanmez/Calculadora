package com.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.calculadora.model.OperacionRequest;
import com.calculadora.service.CalculadoraService;
import com.calculadora.trace.Tracer;

@SpringBootTest
class CalculadoraApplicationTests {

	private CalculadoraService calculadoraService;
    private Tracer tracer;

    @BeforeEach
    public void setUp() {
        tracer = mock(Tracer.class);
        calculadoraService = new CalculadoraService(tracer);
    }

    @Test
    public void testSumar() {
        // Arrange
        OperacionRequest request = new OperacionRequest(BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        // Act
        BigDecimal result = calculadoraService.sumar(request);

        // Assert
        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    public void testRestar() {
        // Arrange
    	OperacionRequest request = new OperacionRequest(BigDecimal.valueOf(5), BigDecimal.valueOf(2));

        // Act
        BigDecimal result = calculadoraService.restar(request);

        // Assert
        assertEquals(BigDecimal.valueOf(3), result);
    }

}
