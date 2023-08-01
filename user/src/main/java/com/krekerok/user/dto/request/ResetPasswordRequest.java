package com.krekerok.user.dto.request;

import com.krekerok.user.util.validator.PasswordEquality;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordEquality(password = "newPassword", confirmationPassword = "confirmationPassword")
public class ResetPasswordRequest {

    @NotBlank(message = "Current password cannot be empty")
    @Size(min = 6, max = 25, message = "Current password min size is 2 symbols and max size is 30 symbols")
    private String currentPassword;

    @NotBlank(message = "New cannot be empty")
    @Size(min = 6, max = 25, message = "new password min size is 2 symbols and max size is 30 symbols")
    private String newPassword;

    @NotBlank(message = "Confirmation password cannot be empty")
    @Size(min = 6, max = 25, message = "Confirmation password min size is 2 symbols and max size is 30 symbols")
    private String confirmationPassword;
}