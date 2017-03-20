package com.tcs.poc.drools.springcamel.service;

import com.tcs.poc.drools.springcamel.model.Product;
import com.tcs.poc.drools.springcamel.model.User;

public interface FinancialService {
	boolean processOrder(User user, Product product);
}
