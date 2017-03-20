package com.tcs.poc.drools.springcamel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcs.poc.drools.springcamel.model.Product;
import com.tcs.poc.drools.springcamel.model.User;
import com.tcs.poc.drools.springcamel.service.FinancialService;
import com.tcs.poc.drools.springcamel.service.ProductService;

@Component("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    CamelContext camelContext;

    @Autowired
    FinancialService financialService;

    public void runProductLogic(User user, Product product) {
        LOGGER.debug("Running product logic - first acceptance Route, then discount Route");
        
        ArrayList objToEvaluate = new ArrayList();
        objToEvaluate.add(user);
        objToEvaluate.add(product);
        
        camelContext.createProducerTemplate().sendBody("direct:acceptanceRoute", objToEvaluate /* Guava: newArrayList(user, product) */);
        camelContext.createProducerTemplate().sendBody("direct:discountRoute", objToEvaluate /*newArrayList(user, product)*/);
        financialService.processOrder(user, product);
    }

}