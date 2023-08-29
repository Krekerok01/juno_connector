package com.krekerok.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerSecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors().and().csrf()
            .disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
            .pathMatchers(HttpMethod.GET, "/users/**")
            .permitAll()
            .pathMatchers("/users/**", "/questions/**", "/comments/**")
            .authenticated()
            .pathMatchers("/authenticate/**")
            .permitAll()
            .and()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .build();
    }
}