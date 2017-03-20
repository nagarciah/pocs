package com.tcs.poc.drools.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcs.poc.drools.spring.model.Product;
import com.tcs.poc.drools.spring.model.User;
import com.tcs.poc.drools.spring.service.FinancialService;
import com.tcs.poc.drools.spring.service.ProductService;

@Component("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Autowired
    FinancialService financialService;
    
    @Autowired
    @Qualifier("usersKSession")
    StatelessKnowledgeSession usersKSession;
    
    @Autowired
    @Qualifier("productsKSession")
    StatelessKnowledgeSession productsKSession;

    public void runProductLogic(User user, Product product) {
        LOGGER.debug("Running product logic - first acceptance Route, then discount Route");
        List objToEvaluate = new ArrayList();
        objToEvaluate.add(user);
        objToEvaluate.add(product);
        
        usersKSession.execute(objToEvaluate);
        productsKSession.execute(objToEvaluate);
        financialService.processOrder(user, product);
    }

}