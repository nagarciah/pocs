package com.tcs.poc.drools.spring.service;

import com.tcs.poc.drools.spring.model.Product;
import com.tcs.poc.drools.spring.model.User;

public interface FinancialService {
	boolean processOrder(User user, Product product);
}
