package com.modusoftware.oauth2.edge.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyCorsFilter {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/").allowedOrigins("http://localhost:8081");
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","HEAD","POST","PUT","DELETE","TRACE","OPTIONS","PATCH");
            }
        };
    }
}