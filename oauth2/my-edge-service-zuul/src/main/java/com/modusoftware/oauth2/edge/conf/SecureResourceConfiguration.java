package com.modusoftware.oauth2.edge.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer // Para rechazar peticiones sin token
//@EnableOAuth2Client   // Para retransmitir el token a los servicios que se
                      // invoquen desde aqui
public class SecureResourceConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().anyRequest().authenticated();
    }

}
