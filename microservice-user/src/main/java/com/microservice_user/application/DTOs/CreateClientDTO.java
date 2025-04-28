package com.microservice_user.application.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CreateClientDTO {

    @NotBlank(message = "The name is mandatory.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s-]+$", message = "This field must have only letters.")
    private String name;

    @NotBlank(message = "The lastname is mantadory.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s-]+$", message = "This field must have only letters.")
    private String lastname;

    @NotBlank(message = "The document number is mandatory")
    @Pattern(regexp = "^\\d+$", message = "This field must have only numbers.")
    private String documentNumber;


    @NotBlank(message = "The phone is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "The format is not valid (it must have between 10 and 13 digits and may start with '+')")
    private String phoneNumber;

    @NotBlank(message = "The email is mandatory.")
    @Email(message = "The format is not valid.")
    private String email;

    @NotBlank(message = "The password is mandatory.")
    @Size(min = 8, message = "The password must have at leat 8 characters.")
    private String password;
}
