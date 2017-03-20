package com.tcs.poc.drools.springcamel.model;

public enum DecisionType {
	ACCEPTED,
	REJECTED;
	
	public static DecisionType fromValue(String value) {
		if(ACCEPTED.name().equalsIgnoreCase(value)) {
			return ACCEPTED;
		}else {
			return REJECTED;
		}
	}
}
