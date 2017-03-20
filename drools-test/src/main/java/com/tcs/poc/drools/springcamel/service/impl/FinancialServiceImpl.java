package com.tcs.poc.drools.springcamel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tcs.poc.drools.springcamel.model.DecisionType;
import com.tcs.poc.drools.springcamel.model.Product;
import com.tcs.poc.drools.springcamel.model.User;
import com.tcs.poc.drools.springcamel.service.FinancialService;


@Component("financialServiceImpl")
public class FinancialServiceImpl implements FinancialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinancialServiceImpl.class);

    public boolean processOrder(User user, Product product) {
        if(DecisionType.ACCEPTED.equals(user.getDecision())){
            LOGGER.debug("User has been approved - processing the order...");
            return true;
        }

        LOGGER.debug("Sorry, user has been rejected...");
        return false;
    }
}