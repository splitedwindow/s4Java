package com.roman.app.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/customers", "orders", "/chefregister", "/register").permitAll() // Allow public access to specific endpoints
                        .requestMatchers("/orders", "/dishes").hasRole("CUSTOMER")
                        .requestMatchers("/dish", "/dishes").hasRole("CHEF")
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .formLogin(login -> login.loginPage("/").permitAll());
        // Enable HTTP Basic authentication
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("customer")
                .password(passwordEncoder().encode("password"))
                .roles("CUSTOMER")
                .build());
        manager.createUser(User.withUsername("chef")
                .password(passwordEncoder().encode("chef"))
                .roles("CHEF")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
