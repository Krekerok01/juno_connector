package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import com.krekerok.forum.repository.QuestionRepository;
import com.krekerok.forum.service.QuestionService;
import com.krekerok.forum.util.mapper.AppMapper;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AppMapper mapper;

    @Override
    public ResponseEntity<QuestionResponse> openQuestion(QuestionRequest questionRequest,
        HttpServletRequest httpRequest) {

        Long authorId = 1L;
        Question question = buildQuestion(authorId, questionRequest.getQuestionText(), LocalDateTime.now());
        questionRepository.save(question);

        return new ResponseEntity<>(mapper.toQuestionResponse(question), HttpStatus.CREATED);
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
