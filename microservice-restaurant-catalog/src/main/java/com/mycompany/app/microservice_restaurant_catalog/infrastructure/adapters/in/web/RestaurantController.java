package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.in.web;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateRestaurantRequestDTO;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.in.CreateRestaurantUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurantUseCase;

    public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
    }

     @PostMapping("/create")
    public ResponseEntity<Void> createRestaurante(@Valid @RequestBody CreateRestaurantRequestDTO request){
       createRestaurantUseCase.createRestaurant(request);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
