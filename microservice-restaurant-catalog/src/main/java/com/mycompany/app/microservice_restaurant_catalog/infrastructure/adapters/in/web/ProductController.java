package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.in.web;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateProductRequestDTO;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.in.CreateProductUseCase;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.UserValidationPort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final UserValidationPort userValidationPort;

    private final CreateProductUseCase createProductUseCase;

    public ProductController(UserValidationPort userValidationPort, CreateProductUseCase createProductUseCase) {
        this.userValidationPort = userValidationPort;
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        Long authenticatedUserId = userValidationPort.getUserIdByEmail(authenticatedUserEmail)
                        .orElseThrow( () -> new RuntimeException("Authenticated user not found with email: " + authenticatedUserEmail) );

        createProductUseCase.createProduct(request, authenticatedUserId);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
