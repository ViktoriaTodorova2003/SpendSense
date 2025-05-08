package com.finance.expenseservice.Security; // Adjust your package name accordingly

//@Configuration
//public class SecurityConfig {
//
//    private final AuthHeaderFilter authHeaderFilter;
//
//    public SecurityConfig(AuthHeaderFilter authHeaderFilter) {
//        this.authHeaderFilter = authHeaderFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/**").authenticated()  // Protect the /api/** endpoints
//                                .anyRequest().permitAll()  // Allow all other requests
//                )
//                .addFilterBefore(authHeaderFilter, UsernamePasswordAuthenticationFilter.class);  // Add the custom filter
//
//        return http.build();
//    }
//}
//

