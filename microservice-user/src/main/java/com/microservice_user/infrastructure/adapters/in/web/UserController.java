package com.microservice_user.infrastructure.adapters.in.web;


import com.microservice_user.application.DTOs.*;
import com.microservice_user.application.DTOs.response.OwnerValidationResponseDTO;
import com.microservice_user.application.ports.in.*;
import com.microservice_user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final CreateOwnerUseCase createOwnerUseCase;
    private final LoginUseCase loginUseCase;
    private final AuthenticatedUserPort authenticatedUserPort;
    private final ValidateOwnerRoleUseCase validateOwnerRoleUseCase;

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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(loginUseCase.login(loginRequestDTO));
    }


    //   --------- ENDPOINTS PARA COMUNICACIÓN ENTRE MICROSERVICIOS ----------
    @GetMapping("/internal/validate-owner/{userId}")
    public OwnerValidationResponseDTO validateOwner(@PathVariable("userId") Long id){
        boolean result = validateOwnerRoleUseCase.validateRole(id);
        OwnerValidationResponseDTO ownerValidationResponseDTO = new OwnerValidationResponseDTO();
        ownerValidationResponseDTO.setValidOwner(result);
        return ownerValidationResponseDTO;
    }



    //   ------ ENDPOINTS DE PRUEBA ------------
    @GetMapping("/protected-test") // Un endpoint GET simple
    // Spring Security se encargara de verificar si hay un usuario autenticado gracias a anyRequest().authenticated()
    public ResponseEntity<String> protectedTestEndpoint() {
        // Si llegamos aqui, significa que el usuario fue autenticado correctamente por el filtro JWT
        return ResponseEntity.ok("Acceso concedido! Eres un usuario autenticado.");
    }

    @GetMapping("/my-info")
    public ResponseEntity<AuthenticatedUserDetails> getAuthenticatedUserInfo(){
        AuthenticatedUserDetails userDetails = authenticatedUserPort.getAuthenticatedUserDetails();
        return ResponseEntity.ok(userDetails);
    }



}
