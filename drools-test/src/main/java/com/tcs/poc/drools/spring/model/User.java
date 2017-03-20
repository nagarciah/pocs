package com.tcs.poc.drools.spring.model;

public class User {
	private String userName;
    private int userAge;
    private CountryType userCountry;
    private DecisionType decision;
    private String decisionDescription;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public CountryType getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(CountryType userCountry) {
		this.userCountry = userCountry;
	}
	public DecisionType getDecision() {
		return decision;
	}
	public void setDecision(DecisionType decision) {
		this.decision = decision;
	}
	public String getDecisionDescription() {
		return decisionDescription;
	}
	public void setDecisionDescription(String decisionDescription) {
		this.decisionDescription = decisionDescription;
	}
}
