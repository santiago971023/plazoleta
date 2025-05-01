package com.microservice_user.application.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "This field (email) is mandatory.")
    private String email;

    @NotBlank(message = "This field (password) is mandatory.")
    private String password;

}
