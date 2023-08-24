package com.krekerok.forum.service;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface QuestionService {

    QuestionResponse openQuestion(QuestionRequest questionRequest, HttpServletRequest httpRequest);

    QuestionResponse closeQuestion(Long questionId, HttpServletRequest httpRequest);
}