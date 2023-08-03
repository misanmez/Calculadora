package com.calculadora.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacionRequest {

	
	private BigDecimal operando1;
    private BigDecimal operando2;

}
