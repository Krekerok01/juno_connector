package com.krekerok.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 30, message = "Username min size is 2 symbols and max size is 30 symbols")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password min size is 2 symbols and max size is 30 symbols")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String email;
}