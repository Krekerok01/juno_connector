package com.krekerok.forum.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "Comment text cannot be empty")
    @Size(min = 1, max = 500, message = "Comment text min size is 1 symbol and max size is 500 symbols")
    private String commentText;

}