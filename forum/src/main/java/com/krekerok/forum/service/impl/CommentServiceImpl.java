package com.krekerok.forum.service.impl;

import com.krekerok.forum.dto.request.CommentRequest;
import com.krekerok.forum.entity.Comment;
import com.krekerok.forum.entity.Question;
import com.krekerok.forum.exception.AccessException;
import com.krekerok.forum.repository.CommentRepository;
import com.krekerok.forum.service.CommentService;
import com.krekerok.forum.service.QuestionService;
import com.krekerok.forum.util.getter.UserInfoGetter;
import com.krekerok.forum.util.security.JwtUtil;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final QuestionService questionService;
    private final UserInfoGetter userInfoGetter;

    @Override
    public Long createComment(Long questionId, CommentRequest commentRequest, HttpServletRequest httpRequest) {

        Question question = questionService.getQuestionById(questionId);
        if (!question.isOpenForDiscussion())
            throw new AccessException("The question is closed for discussion");

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long userRequestId = userInfoGetter.getUserIdByEmail(userEmail);

        Comment comment = buildComment(commentRequest.getCommentText(), userRequestId, question);
        commentRepository.save(comment);

        return comment.getCommentId();
    }

    private Comment buildComment(String commentText, Long userRequestId, Question question) {
        return Comment.builder()
            .authorId(userRequestId)
            .commentText(commentText)
            .creationDate(LocalDateTime.now())
            .modificationDate(LocalDateTime.now())
            .question(question)
            .build();
    }
}
