package com.krekerok.forum.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    @NotBlank(message = "Question text cannot be empty")
    @Size(min = 5, max = 1000, message = "Question text min size is 5 symbols and max size is 1000 symbols")
    private String questionText;
}
