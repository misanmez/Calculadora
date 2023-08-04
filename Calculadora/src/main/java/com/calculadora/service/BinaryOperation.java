package com.calculadora.service;

import java.math.BigDecimal;

public interface BinaryOperation {
	
	BigDecimal calcular(BigDecimal a, BigDecimal b);
	
	String getOperator();
}
