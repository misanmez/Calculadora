package com.calculadora.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperacionRequest {

	private BigDecimal operando1;
    private BigDecimal operando2;

}
