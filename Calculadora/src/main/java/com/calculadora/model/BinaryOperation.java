package com.calculadora.model;

import java.math.BigDecimal;

@FunctionalInterface
public interface BinaryOperation {
	BigDecimal calcular(BigDecimal a, BigDecimal b);
}
