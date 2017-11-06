package com.modusoftware.oauth2.auth.accounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AccountConfiguration {

    /**
     * Crea un bean para que el {@link AuthenticationManager} busque informacion
     * de autenticacion
     * 
     * @param accountRepository Repositorio de cuentas
     * @return
     */
    @Bean//("myUserDetailsService")
    UserDetailsService userDetailsService(AccountRepository accountRepository) {

        return username -> accountRepository
                .findByUsername(username)
                .map(account -> {
                    boolean active = account.isActive();
                    return new User(account.getUsername(), account.getPassword(), active, active, active, active,
                            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER"));
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found!", username)));
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
