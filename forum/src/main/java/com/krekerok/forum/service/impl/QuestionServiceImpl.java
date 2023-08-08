package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import com.krekerok.forum.repository.QuestionRepository;
import com.krekerok.forum.service.QuestionService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public ResponseEntity<QuestionResponse> openQuestion(QuestionRequest questionRequest,
        HttpServletRequest httpRequest) {

        Question question = Question.builder()
            .authorId(1L)
            .isOpen(true)
            .questionText(questionRequest.getQuestionText())
            .openingDate(LocalDateTime.now())
            .modificationDate(LocalDateTime.now())
            .build();

        questionRepository.save(question);
        return null;
    }
}
