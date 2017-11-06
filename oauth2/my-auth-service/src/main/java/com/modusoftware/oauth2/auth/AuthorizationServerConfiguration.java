package com.modusoftware.oauth2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final ClientDetailsService clientDetailsService;

    //private final UserDetailsService userDetailsService;

    @Autowired
    public AuthorizationServerConfiguration(
            AuthenticationManager authenticationManager,
            ClientDetailsService clientDetailsService/*,
            @Qualifier("myUserDetailsService") UserDetailsService userDetailsService*/) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        //this.userDetailsService = userDetailsService;
    }

    /**
     * Para buscar informacion de los clientes
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * Para enlazar con Spring Security para delegarle la autenticacion
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer oauthServer) throws Exception {
        oauthServer.authenticationManager(authenticationManager);
        //oauthServer.userDetailsService(userDetailsService);
    }
   /* 
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        //security.passwordEncoder(passwordEncoder);
    }
*/
}
