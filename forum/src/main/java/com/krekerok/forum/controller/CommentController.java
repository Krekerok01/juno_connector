package com.krekerok.forum.controller;

import com.krekerok.forum.dto.request.CommentRequest;
import com.krekerok.forum.dto.request.QuestionRequest;
import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.service.CommentService;
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
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{questionId}")
    public ResponseEntity<Long> createComment(@PathVariable("questionId") Long questionId,
                                              @RequestBody @Valid CommentRequest commentRequest,
                                              HttpServletRequest httpRequest){
        return new ResponseEntity<>(commentService.createComment(questionId, commentRequest, httpRequest), HttpStatus.CREATED);
    }

}