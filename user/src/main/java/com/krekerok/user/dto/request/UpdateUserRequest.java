package com.krekerok.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "First name must contain only letters")
    @Size(min = 2, max = 30, message = "First name min size is 2 symbols and max size is 30 symbols")
    private String firstName;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Last name must contain only letters")
    @Size(min = 2, max = 30, message = "Last name min size is 2 symbols and max size is 30 symbols")
    private String lastName;

    @Email(message = "Email must be a valid email address")
    private String email;
}