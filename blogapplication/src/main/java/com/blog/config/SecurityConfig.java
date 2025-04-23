package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.security.JwtAuthenticationentryPoint;
import com.blog.security.JwtAutheticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtAuthenticationentryPoint jwtAuthenticationentryPoint;
	
	@Autowired
	private JwtAutheticationFilter jwtAutheticationFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf-> csrf.disable())
		.cors(cors-> cors.disable())
		.authorizeHttpRequests(
				
				auth->
				
				 auth.requestMatchers("/api/users/**").authenticated()
				 .requestMatchers("/auth/login").permitAll()
				 .requestMatchers("/auth/create-user").permitAll()
				 .anyRequest().authenticated())
		.exceptionHandling(ex-> ex.authenticationEntryPoint(jwtAuthenticationentryPoint))
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(jwtAutheticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
				 
				
				
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
	}
	

} 