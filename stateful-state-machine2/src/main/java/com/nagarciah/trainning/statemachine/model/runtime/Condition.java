package com.nagarciah.trainning.statemachine.model;

import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

public class Condition {
	
	public Condition(String qualifiedMethodName){
		try {
			this.evaluatingClass = Class.forName(qualifiedMethodName);
		} catch (ClassNotFoundException e) {
			BeanUtils.instantiateClass(evaluatingClass);
		}
	}
	
	Method evaluatingMethod;
	
	Class<?> evaluatingClass;
}
