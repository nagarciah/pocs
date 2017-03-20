package com.nagarciah.pocs.ldap.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	@Value("${web.security.useInMemoryProvider}")
	boolean useInMemoryProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
*/
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*if(useInMemoryProvider){*/
			auth.inMemoryAuthentication()
				.withUser("user")
				.password("pwd")
				.roles("USER");
		/*}else{
			auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http
			.csrf().disable() // TODO volver a activar, una vez sepa como hacer que inserte el token en el formulario
			.authorizeRequests()
				//.antMatchers("/", "/home").permitAll()
				//.antMatchers("/fonts/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/home")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.permitAll();*/
		http
    	.csrf() // Para que el POST funcione con AngularJS
    		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())/*
    		.and()
    	.httpBasic()
    		.and()
    	.authorizeRequests()
    	    .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and()
        .logout()
        	.logoutSuccessUrl("/")
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.invalidateHttpSession(true)
            .permitAll()*/;
	}
	
	@Override
	public void configure(WebSecurity security){
	    security.ignoring().antMatchers("/font-awesome/**", "/api/**", "/img/**", "/contact/**");
	}
}