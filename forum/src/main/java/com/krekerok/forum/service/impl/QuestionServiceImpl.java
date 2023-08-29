package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import com.krekerok.forum.exception.EntityNotFoundException;
import com.krekerok.forum.exception.VerificationException;
import com.krekerok.forum.repository.QuestionRepository;
import com.krekerok.forum.service.QuestionService;
import com.krekerok.forum.util.getter.UserInfoGetter;
import com.krekerok.forum.util.mapper.AppMapper;
import com.krekerok.forum.util.security.JwtUtil;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AppMapper mapper;
    private final UserInfoGetter userInfoGetter;


    @Override
    public QuestionResponse openQuestion(QuestionRequest questionRequest,
        HttpServletRequest httpRequest) {

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long authorId = userInfoGetter.getUserIdByEmail(userEmail);

        Question question = buildQuestion(authorId, questionRequest.getQuestionText(), LocalDateTime.now());
        questionRepository.save(question);

        return mapper.toQuestionResponse(question);
    }

    @Transactional
    @Override
    public QuestionResponse closeQuestion(Long questionId, HttpServletRequest httpRequest) {

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long userRequestId = userInfoGetter.getUserIdByEmail(userEmail);

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

    private Question buildQuestion(Long authorId, String questionText, LocalDateTime now) {
        return Question.builder()
            .authorId(authorId)
            .openForDiscussion(true)
            .questionText(questionText)
            .openingDate(now)
            .modificationDate(now)
            .build();
    }
}