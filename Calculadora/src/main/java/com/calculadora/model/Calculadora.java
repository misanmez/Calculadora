package com.calculadora.model;

import java.math.BigDecimal;

public class Calculadora {
	
	public BigDecimal suma(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal resta(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

}