package com.calculadora.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OperacionSuma implements BinaryOperation {
	
    @Override
    public BigDecimal calcular(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

	@Override
	public String getOperator() {
		return "sumar";
	}
}