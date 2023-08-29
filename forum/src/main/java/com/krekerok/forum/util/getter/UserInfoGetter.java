package com.krekerok.forum.util.getter;

import com.krekerok.forum.exception.ServiceClientException;
import com.krekerok.forum.exception.ServiceUnavailableException;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserInfoGetter {

    private final EurekaClient eurekaClient;
    private final WebClient webClient;

    public Long getUserIdByEmail(String userEmail) {
        String uri = getUserUrlFromEureka() + "/api/v1/users/email/" + userEmail;
        return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> handleTicketServiceError(response))
            .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new ServiceUnavailableException("User service is unavailable. Try again later.")))
            .bodyToMono(Long.class)
            .block();
    }

    private String getUserUrlFromEureka() {
        try {
            return eurekaClient.getNextServerFromEureka("user-service", false).getHomePageUrl();
        } catch (RuntimeException e) {
            throw new ServiceUnavailableException("User service is unavailable. Try again later.");
        }
    }

    private Mono<? extends Throwable> handleTicketServiceError(ClientResponse response) {
        if (response.statusCode() == HttpStatus.NOT_FOUND) {
            return Mono.error(new ServiceClientException("User(s) not found."));
        } else {
            return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new ServiceClientException("Bad request. Error: " + errorBody)));
        }
    }
}