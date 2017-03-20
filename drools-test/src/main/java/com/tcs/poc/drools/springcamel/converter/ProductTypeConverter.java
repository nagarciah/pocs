package com.tcs.poc.drools.springcamel.converter;

import java.util.List;

import org.apache.camel.Converter;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.poc.drools.springcamel.model.Product;

@Converter
public class ProductTypeConverter {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeConverter.class);
 
    @Converter
    public static Command toCommandFromList(List inputList) {
        LOGGER.debug("Executing ProductTypeConverter's toCommandFromList method");
        return CommandFactory.newInsertElements(inputList);
    }
 
    @Converter
    public static Command toCommand(Product product) {
        LOGGER.debug("Executing ProductTypeConverter's toCommand method");
        return CommandFactory.newInsert(product);
    }
}