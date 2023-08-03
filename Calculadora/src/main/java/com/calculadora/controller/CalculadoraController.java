package com.calculadora.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadora.model.OperacionRequest;
import com.calculadora.service.CalculadoraService;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

	private final CalculadoraService calculadoraService;

    @Autowired
    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/sumar")
    public ResponseEntity<BigDecimal> sumar(@RequestBody OperacionRequest request) {
        BigDecimal result = calculadoraService.sumar(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/restar")
    public ResponseEntity<BigDecimal> restar(@RequestBody OperacionRequest request) {
        BigDecimal result = calculadoraService.restar(request);
        return ResponseEntity.ok(result);
    }
}
