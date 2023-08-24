package com.krekerok.forum.controller;

import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.service.QuestionService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ResponseEntity<>(questionService.openQuestion(questionRequest, httpRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{questionId}/close")
    public QuestionResponse closeQuestion(@PathVariable(name = "questionId") Long questionId,
        HttpServletRequest httpRequest){
        return questionService.closeQuestion(questionId, httpRequest);
    }
}