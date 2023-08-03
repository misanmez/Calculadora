package com.calculadora.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculadora.model.OperacionRequest;
import com.calculadora.trace.Tracer;


@Service
public class CalculadoraService {

	private final Tracer tracer;

    @Autowired
    public CalculadoraService(Tracer tracer) {
        this.tracer = tracer;
    }

    public BigDecimal sumar(OperacionRequest request) {
    	BigDecimal operando1 = request.getOperando1();
        BigDecimal operando2 = request.getOperando2();

        BigDecimal result = operando1.add(operando2);

        tracer.trace(result);

        return result;
    }

    public BigDecimal restar(OperacionRequest request) {
    	BigDecimal operando1 = request.getOperando1();
        BigDecimal operando2 = request.getOperando2();

        BigDecimal result = operando1.subtract(operando2);
   
        tracer.trace(result);

        return result;
    }
}
