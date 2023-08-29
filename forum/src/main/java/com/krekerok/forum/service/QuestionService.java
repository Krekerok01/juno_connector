package com.krekerok.forum.service;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import javax.servlet.http.HttpServletRequest;

public interface QuestionService {

    QuestionResponse openQuestion(QuestionRequest questionRequest, HttpServletRequest httpRequest);

    QuestionResponse closeQuestion(Long questionId, HttpServletRequest httpRequest);

    Question getQuestionById(Long questionId);
}