package com.krekerok.forum.util.mapper;

import com.krekerok.forum.dto.response.QuestionResponse;
import com.krekerok.forum.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppMapper {

    QuestionResponse toQuestionResponse(Question question);
}