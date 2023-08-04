package com.calculadora.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OperacionResta implements BinaryOperation {
	
    @Override
    public BigDecimal calcular(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
    
    @Override
	public String getOperator() {
    	return "restar";
	}
}