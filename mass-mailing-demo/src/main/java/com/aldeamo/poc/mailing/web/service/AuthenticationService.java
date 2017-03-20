package com.aldeamo.poc.mailing.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aldeamo.poc.mailing.model.UserInfo;
import com.aldeamo.poc.mailing.web.conf.WebSecurityConfig;
import com.aldeamo.poc.mailing.web.dto.UserAuthenticationInfo;

/**
 * Servicio de autenticación personalizada. Para autenticar al usuario, basta
 * con encontrar sus datos mediante el método
 * {@link AuthenticationService#loadUserByUsername(String)}}
 * 
 * Este servicio es invocado por Spring Security y se configura en
 * {@link WebSecurityConfig#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)}
 * 
 * @author nelson
 *
 */
@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserService userService;

	/**
	 * Verifica que el usuario exista en el repositorio personalizado. Para
	 * denegar la autenticación, se debe lanzar una excepción
	 * {@link UsernameNotFoundException}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userService.getUserInfo(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Usuario %s no existe", username)));

		return new UserAuthenticationInfo(userInfo);
	}

}
