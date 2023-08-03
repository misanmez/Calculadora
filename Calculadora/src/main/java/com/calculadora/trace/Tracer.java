package com.calculadora.trace;

import org.springframework.stereotype.Component;

import io.corp.calculator.TracerAPI;

@Component
public class Tracer implements TracerAPI {

	@Override
	public <T> void trace( final T result ) {
        System.out.println( "resultado :: " + result.toString() );
    }

}
