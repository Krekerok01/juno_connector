package com.krekerok.profile.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotBlank(message = "User information cannot be empty")
    @Size(min = 2, max = 1000, message = "User information min size is 2 symbols and max size is 1000 symbols")
    private String userInformation;

    @NotBlank(message = "Specialization cannot be empty")
    @Size(min = 2, max = 30, message = "Specialization min size is 2 symbols and max size is 30 symbols")
    private String specialization;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Country must contain only letters")
    @Size(min = 2, max = 30, message = "Country min size is 2 symbols and max size is 30 symbols")
    private String country;

    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "City must contain only letters")
    @Size(min = 2, max = 30, message = "City min size is 2 symbols and max size is 30 symbols")
    private String city;
}