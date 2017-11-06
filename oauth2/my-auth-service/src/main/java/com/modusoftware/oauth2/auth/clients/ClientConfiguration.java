package com.modusoftware.oauth2.auth.clients;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

@Configuration
public class ClientConfiguration {

    /**
     * Crea un bean para que el {@link AuthorizationServerConfigurer} busque informacion
     * de autorizacion de los clientes
     * 
     * @param clientRepository
     * @return
     */
    @Bean
    ClientDetailsService clientDetailsService(ClientRepository clientRepository) {
        return clientId -> clientRepository
                .findByClientId(clientId)
                .map(client -> {
                    BaseClientDetails details = new BaseClientDetails(
                            client.getClientId(), 
                            null, 
                            client.getScopes(),
                            client.getAuthorizedGrantTypes(), 
                            client.getAuthorities());
            
                    details.setClientSecret(client.getSecret());

                    // Para autorizar automaticamente todos loes permisos que solicta el cliente
                    details.setAutoApproveScopes( Arrays.asList(client.getAutoApproveScopes().split(",")) ); 

                    String greetingsClientRedirectUri = client.getRedirectUri();

                    details.setRegisteredRedirectUri(Collections.singleton(greetingsClientRedirectUri));
                    return details;
                })
                .orElseThrow(() -> new ClientRegistrationException(String.format("no client %s registered", clientId)));
    }
}
