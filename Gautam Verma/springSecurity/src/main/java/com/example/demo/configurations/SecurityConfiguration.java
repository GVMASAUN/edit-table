package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.*;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.SecurityUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/register", "/WEB-INF/jsp/**")
						.permitAll().requestMatchers("/admin").hasAnyRole("ADMIN").requestMatchers("/dev")
						.hasAnyRole("ADMIN", "DEVELOPER").anyRequest().authenticated())
				.formLogin(withDefaults())
				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).permitAll());
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityUserDetailService securityUserDetailService() {
		return new SecurityUserDetailService();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(securityUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
}
