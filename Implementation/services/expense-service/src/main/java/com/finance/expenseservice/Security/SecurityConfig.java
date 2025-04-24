package com.finance.expenseservice.Security; // Adjust your package name accordingly

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthHeaderFilter authHeaderFilter;

    public SecurityConfig(AuthHeaderFilter authHeaderFilter) {
        this.authHeaderFilter = authHeaderFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").authenticated()  // Protect the /api/** endpoints
                                .anyRequest().permitAll()  // Allow all other requests
                )
                .addFilterBefore(authHeaderFilter, UsernamePasswordAuthenticationFilter.class);  // Add the custom filter

        return http.build();
    }
}


