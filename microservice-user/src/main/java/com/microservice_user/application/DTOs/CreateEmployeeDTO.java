package com.microservice_user.application.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeDTO {
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

    @NotNull
    private LocalDate birthday;

    /*Añadir la validación de negocio en la implementación del caso de uso CreateEmpleadoService para verificar
     que la fecha de nacimiento sea de hace al menos 18 años (isAfter(LocalDate.now().minusYears(18))).*/
}
