package com.modusoftware.oauth2.client;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class WebClientApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(WebClientApplication.class, args);
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .antMatcher("/**")
        .authorizeRequests()
            .antMatchers("/", "/login**", "/webjars/**")
            .permitAll()
        .anyRequest()
            .authenticated()
        .and().logout()
            .logoutSuccessUrl("/").permitAll()
        // Parasoportar la forma en que Angular maneja el header CSRF/XSRF
        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
