package com.krekerok.forum.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long questionId;
    private Long authorId;
    private boolean openForDiscussion;
    private String questionText;
    private LocalDateTime openingDate;
    private LocalDateTime modificationDate;
}