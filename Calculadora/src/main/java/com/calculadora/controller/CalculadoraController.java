package com.calculadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadora.dto.ResultResponse;
import com.calculadora.exception.InvalidOperatorException;
import com.calculadora.exception.InvalidParameterException;
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

    @PostMapping("/{operator}")
    public ResponseEntity<?> calculate(
            @PathVariable String operator,
            @RequestBody OperacionRequest request) throws InvalidOperatorException, InvalidParameterException{
    	
    	ResultResponse resultResponse = calculadoraService.performOperation(operator, request);
        	return ResponseEntity.ok(resultResponse);
    }
}
