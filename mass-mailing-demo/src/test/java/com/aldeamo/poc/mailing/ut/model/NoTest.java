package com.aldeamo.poc.mailing.ut.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

public class NoTest {

	@Test
	public void test(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX");
		System.out.println(sdf.format(new Date()).substring(0, 22)+ ":00");
	}
	
	@Test
	public void testProperties() throws IOException{
		Properties props = new Properties();
		props.load(this.getClass().getResourceAsStream("/application.properties"));
		props.list(System.out);
	}
}
