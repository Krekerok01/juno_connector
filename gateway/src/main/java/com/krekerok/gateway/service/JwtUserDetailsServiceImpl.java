package com.krekerok.gateway.service;

import com.krekerok.gateway.dto.User;
import com.krekerok.gateway.security.JwtUser;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final EurekaClient eurekaClient;
    private final WebClient webClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        String uri = getUsersUrlFromEureka() + "/gateway/check/"+ username;
        System.out.println(">>> URI: " + uri);
        return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(
                new RuntimeException("Ticket service is unavailable. Try again later.")))
            .bodyToMono(User.class)
            .flatMap(user -> Mono.just(JwtUser.builder().role(user.getRole())
                .username(user.getUsername())
                .build()));
    }

    private String getUsersUrlFromEureka() {
        try {
            return eurekaClient.getNextServerFromEureka("user-service", false).getHomePageUrl();
        } catch (RuntimeException e) {
            throw new RuntimeException("User service is unavailable. Try again later.");
        }
    }
}
