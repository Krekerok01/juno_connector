package com.krekerok.gateway.service;

import com.krekerok.gateway.dto.User;
import com.krekerok.gateway.exception.ServiceClientException;
import com.krekerok.gateway.exception.ServiceUnavailableException;
import com.krekerok.gateway.security.JwtUser;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
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
        return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> handleTicketServiceError(response))
            .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new ServiceUnavailableException("Service is unavailable.")))
            .bodyToMono(User.class)
            .flatMap(user -> Mono.just(JwtUser.builder().role(user.getRole())
                .username(user.getUsername())
                .build()));
    }

    private Mono<? extends Throwable> handleTicketServiceError(ClientResponse response) {
        if (response.statusCode() == HttpStatus.NOT_FOUND) {
            return Mono.error(new ServiceClientException("Recourse not found."));
        } else {
            return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(
                    new ServiceClientException("Bad request. Error: " + errorBody)));
        }
    }

    private String getUsersUrlFromEureka() {
        try {
            return eurekaClient.getNextServerFromEureka("user-service", false).getHomePageUrl();
        } catch (RuntimeException e) {
            throw new RuntimeException("User service is unavailable. Try again later.");
        }
    }
}