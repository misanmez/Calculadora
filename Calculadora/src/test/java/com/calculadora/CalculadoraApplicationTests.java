package com.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.calculadora.model.BinaryOperation;
import com.calculadora.model.OperacionRequest;
import com.calculadora.service.CalculadoraService;
import com.calculadora.trace.Tracer;

import io.corp.calculator.TracerAPI;

@RunWith(MockitoJUnitRunner.class)
class CalculadoraApplicationTests {

	@Mock
    private Tracer tracer;

    @InjectMocks
    private CalculadoraService calculadoraService;

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
    
    @Test
    public void testCalcular_OperationSuma_Success() {
        // Arrange
        BigDecimal operando1 = new BigDecimal("10");
        BigDecimal operando2 = new BigDecimal("5");
        OperacionRequest request = new OperacionRequest(operando1, operando2);

        BigDecimal expectedResult = new BigDecimal("15");
        BinaryOperation addOperation = (a, b) -> a.add(b);
       
        // Act
        BigDecimal result = calculadoraService.realizarOperacion(request, addOperation);

        // Assert
        assertEquals(expectedResult, result);
    }

}
