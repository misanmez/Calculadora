package com.calculadora.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculadora.dto.ResultResponse;
import com.calculadora.exception.InvalidOperatorException;
import com.calculadora.exception.InvalidParameterException;
import com.calculadora.model.OperacionRequest;

import io.corp.calculator.TracerImpl;


@Service
public class CalculadoraService {

	private final TracerImpl tracer;
	private final Map<String, BinaryOperation> operaciones;

	@Autowired
    public CalculadoraService(BinaryOperation[] binaryOperations, TracerImpl tracer) {
		this.tracer = tracer;
		operaciones = new HashMap<>();
        for (BinaryOperation operation : binaryOperations) {
        	operaciones.put(operation.getOperator(), operation);
        }
    }

	public ResultResponse performOperation(String operator, OperacionRequest request) {
        BinaryOperation operation = operaciones.get(operator);
        if (operation == null) {
            throw new InvalidOperatorException("Operador no válido: " + operator);
        }

        BigDecimal operand1 = request.getOperando1();
        BigDecimal operand2 = request.getOperando2();

        if (operand1 == null || operand2 == null) {
            throw new InvalidParameterException("Ambos operandos deben ser proporcionados");
        }

        BigDecimal result = operation.calcular(operand1, operand2);

        tracer.trace(result);
        
        return new ResultResponse(result);
    }
}
