package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.repository.QuestionRepository;
import com.krekerok.forum.service.QuestionService;
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
        return null;
    }
}
