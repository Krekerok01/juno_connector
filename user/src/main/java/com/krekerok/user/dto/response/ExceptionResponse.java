package com.krekerok.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private int statusCode;
    private String statusMessage;
}
