package com.modusoftware.oauth2.auth.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecureResourceConfiguration extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_VIEW_URL = "/login";
    private static final String LOGIN_VIEW_NAME = "login";
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
          .antMatchers(LOGIN_VIEW_URL, "/oauth/authorize")
          .and().authorizeRequests().anyRequest().authenticated()
          .and().formLogin().loginPage(LOGIN_VIEW_URL).permitAll()
          .and().logout().permitAll();
    }
    
    /**
     * Para reemplazar el formulario de login por defecto con nuestra propia
     * version en Thymeleaf. Thymeleaf en este proyecto unicamente se 
     * requiere para este proposito
     * 
     * @author nelson
     *
     */
    @Configuration
    public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController(LOGIN_VIEW_URL).setViewName(LOGIN_VIEW_NAME);
        }
    }
}
