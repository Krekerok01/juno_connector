package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import com.krekerok.forum.exception.EntityNotFoundException;
import com.krekerok.forum.exception.ServiceClientException;
import com.krekerok.forum.exception.ServiceUnavailableException;
import com.krekerok.forum.exception.VerificationException;
import com.krekerok.forum.repository.QuestionRepository;
import com.krekerok.forum.service.QuestionService;
import com.krekerok.forum.util.mapper.AppMapper;
import com.krekerok.forum.util.security.JwtUtil;
import com.netflix.discovery.EurekaClient;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AppMapper mapper;
    private final EurekaClient eurekaClient;
    private final WebClient webClient;

    @Override
    public QuestionResponse openQuestion(QuestionRequest questionRequest,
        HttpServletRequest httpRequest) {

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long authorId = getUserIdByEmail(userEmail);

        Question question = buildQuestion(authorId, questionRequest.getQuestionText(), LocalDateTime.now());
        questionRepository.save(question);

        return mapper.toQuestionResponse(question);
    }

    @Transactional
    @Override
    public QuestionResponse closeQuestion(Long questionId, HttpServletRequest httpRequest) {

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long userRequestId = getUserIdByEmail(userEmail);

        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new EntityNotFoundException("Question with id " + questionId + " not found"));

        if (question.getAuthorId().equals(userRequestId)) {
            question.setClosingDate(LocalDateTime.now());
            question.setOpenForDiscussion(false);
            questionRepository.save(question);
            return mapper.toQuestionResponse(question);
        }
        throw new VerificationException("Verification exception");
    }

    private Long getUserIdByEmail(String userEmail) {
        String uri = getUserUrlFromEureka() + "/api/v1/users/email/" + userEmail;
        return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> handleTicketServiceError(response))
            .onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new ServiceUnavailableException("User service is unavailable. Try again later.")))
            .bodyToMono(Long.class)
            .block();
    }

    private Question buildQuestion(Long authorId, String questionText, LocalDateTime now) {
        return Question.builder()
            .authorId(authorId)
            .openForDiscussion(true)
            .questionText(questionText)
            .openingDate(now)
            .modificationDate(now)
            .build();
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
