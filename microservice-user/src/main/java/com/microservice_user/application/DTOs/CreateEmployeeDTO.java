package com.microservice_user.application.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateEmployeeDTO {
    private String name;
    private String lastname;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private String password;
}
