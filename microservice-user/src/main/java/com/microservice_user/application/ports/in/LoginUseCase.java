package com.microservice_user.application.ports.in;

import com.microservice_user.application.DTOs.LoginRequestDTO;
import com.microservice_user.application.DTOs.LoginResponseDTO;

public interface LoginUseCase {

    LoginResponseDTO login(LoginRequestDTO loginDTO);

}
