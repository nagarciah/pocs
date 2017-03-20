package com.nagarciah.trainning.statemachine.model;

import java.lang.reflect.Method;

public class Condition {
	
	public Condition(String qualifiedMethodName){
		try {
			this.evaluatingClass = Class.forName(qualifiedMethodName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se encuentra la clase que implementa la condición: " + qualifiedMethodName, e);
		} 
	}
	
	Method evaluatingMethod;
	
	Class<?> evaluatingClass;
}
