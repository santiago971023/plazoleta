package com.microservice_user.application.ports.in;

import com.microservice_user.domain.User;

public interface CreateEmployeeUseCase {
    User createEmployee(CreateEmployeeUseCase employeeDTO);
}
