package com.modusoftware.oauth2.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MyEdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEdgeServiceApplication.class, args);
	}
}
