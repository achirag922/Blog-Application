package com.dev.blog.constants;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;import com.dev.blog.entities.User;
import com.dev.blog.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.httpBasic(withDefaults());

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		return authBuilder.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
