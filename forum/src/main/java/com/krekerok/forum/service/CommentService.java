package com.krekerok.forum.service;

import com.krekerok.forum.dto.request.CommentRequest;
import javax.servlet.http.HttpServletRequest;

public interface CommentService {

    Long createComment(Long questionId, CommentRequest commentRequest, HttpServletRequest httpRequest);
}