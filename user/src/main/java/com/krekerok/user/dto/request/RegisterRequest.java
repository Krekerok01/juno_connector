package com.krekerok.user.dto.request;

import javax.persistence.Column;
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

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 30, message = "First name min size is 2 symbols and max size is 60 symbols")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 30, message = "Last name min size is 2 symbols and max size is 60 symbols")
    private String lastName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password min size is 2 symbols and max size is 30 symbols")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid email address")
    private String email;
}