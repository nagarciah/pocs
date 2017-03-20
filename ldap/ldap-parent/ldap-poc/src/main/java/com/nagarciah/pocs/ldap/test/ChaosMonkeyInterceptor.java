package com.nagarciah.pocs.ldap.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Utilitario para identificar el tipo de browser
 * @author nelson
 */
public class ChaosMonkeyInterceptor extends HandlerInterceptorAdapter {
	
	private final static Log log = LogFactory.getLog(ChaosMonkeyInterceptor.class);
	
	@Value("${test.randomErrors.generate:false}")
	boolean generateRandomErrors;
	
	@Value("${test.randomErrors.rate:0.1}")
	double errorRate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(generateRandomErrors){
			if(Math.random()<errorRate){
				throw new RuntimeException("Error aleatorio generado");
			}
		}
		
		return true;	
	}

}
