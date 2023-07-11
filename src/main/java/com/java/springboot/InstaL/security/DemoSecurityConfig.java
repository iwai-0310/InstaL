package com.java.springboot.InstaL.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.java.springboot.InstaL.service.UserService;

@Configuration
public class DemoSecurityConfig {
	//add support for jdbc.. using no more hardcoded users
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
		//define a query to retrieve a user by name
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select user_id,pw,active from members where user_id=?");
		//define a query to retrieve roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select user_id,role from roles where user_id=?");
		return new JdbcUserDetailsManager(dataSource);
	}
	//Configuring security of web path for custom login
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests(configurer ->
			configurer
					.requestMatchers("/").hasRole("EMPLOYEE")
					.requestMatchers("/leaders/**").hasRole("ADMIN")
					.anyRequest().authenticated()
				)
		.exceptionHandling(configurer->
					configurer
						.accessDeniedPage("/access-denied")
					)
		.formLogin(form ->
				form
						.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll()
						)
		.logout(logout -> logout.permitAll()
				);
				
		
		return http.build();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//authenticationProvider Bean definition
	@Bean
	public DaoAuthenticationProvider authenticaitonProvider(UserService userService) {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails john=User.builder()
				.username("john")
				.password("{noop}test123")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails marry=User.builder()
				.username("marry")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER")
				.build();
		
		UserDetails susan=User.builder()
				.username("susan")
				.password("{noop}test123")
				.roles("EMPLOYEE","MANAGER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john,marry,susan);
	}*/

}
