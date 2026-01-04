package com.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/auth/**").permitAll()
        	    .requestMatchers("/books/addBook").hasRole("ADMIN")
        	    .requestMatchers("/reports/**").hasRole("ADMIN")
        	    .requestMatchers("/books/**").hasAnyRole("USER","ADMIN")
        	    .requestMatchers("/users/**").hasAnyRole("USER","ADMIN")
            .anyRequest().authenticated())
        .httpBasic(org.springframework.security.config.Customizer.withDefaults());

    return http.build();
	}
}
