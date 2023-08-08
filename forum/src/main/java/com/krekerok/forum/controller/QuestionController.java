package com.krekerok.forum.controller;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.service.QuestionService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/open")
    public ResponseEntity<QuestionResponse> openQuestion(@RequestBody @Valid QuestionRequest questionRequest,
        HttpServletRequest httpRequest){
        return questionService.openQuestion(questionRequest, httpRequest);
    }
}