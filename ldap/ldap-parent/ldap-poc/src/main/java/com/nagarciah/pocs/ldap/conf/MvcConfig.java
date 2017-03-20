package com.nagarciah.pocs.ldap.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("forward:/reports");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/reports").setViewName("metrics");
		registry.addViewController("/success").setViewName("success");
	}
	
}
