package com.microservice_user.infrastructure.adapters.in.web;


import com.microservice_user.application.DTOs.CreateClientDTO;
import com.microservice_user.application.DTOs.CreateEmployeeDTO;
import com.microservice_user.application.DTOs.CreateOwnerDTO;
import com.microservice_user.application.ports.in.CreateClientUseCase;
import com.microservice_user.application.ports.in.CreateEmployeeUseCase;
import com.microservice_user.application.ports.in.CreateOwnerUseCase;
import com.microservice_user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final CreateOwnerUseCase createOwnerUseCase;

    @PostMapping("/create-client")
    public ResponseEntity<User> createClient(@Valid @RequestBody CreateClientDTO clientDTO){
        User createdUser = createClientUseCase.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/create-employee")
    public ResponseEntity<User> createEmployee(@Valid @RequestBody CreateEmployeeDTO employeeDTO){
        User createdUser = createEmployeeUseCase.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/create-owner")
    public ResponseEntity<User> createOwner(@Valid @RequestBody CreateOwnerDTO ownerDTO){
        User createdUser = createOwnerUseCase.createOwner(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
