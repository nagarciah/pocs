package com.modusoftware.oauth2.resource.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@Profile("secure")
@EnableResourceServer // Para rechazar peticiones sin token
@EnableOAuth2Client   // Para retransmitir el token a los servicios que se invoquen desde aqui
public class OAuthResourceConfiguration {

}
