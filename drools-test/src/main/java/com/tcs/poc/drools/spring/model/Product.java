package com.tcs.poc.drools.spring.model;

public class Product {
	private String name;
    private ProductType type;
	private double price;
    private CountryType CountryOfOrigin;
    private String AdditionalInfo;
    private int Quantity;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CountryType getCountryOfOrigin() {
		return CountryOfOrigin;
	}
	public void setCountryOfOrigin(CountryType countryOfOrigin) {
		CountryOfOrigin = countryOfOrigin;
	}
	public String getAdditionalInfo() {
		return AdditionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		AdditionalInfo = additionalInfo;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
