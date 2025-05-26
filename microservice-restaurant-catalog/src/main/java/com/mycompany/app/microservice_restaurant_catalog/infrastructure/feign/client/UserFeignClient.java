package com.mycompany.app.microservice_restaurant_catalog.infrastructure.feign.client;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.internal.UserIdResponseDTO;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.feign.client.dto.OwnerValidationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-user")
public interface UserFeignClient {

    @GetMapping("/users/internal/validate-owner/{userId}")
    OwnerValidationResponseDTO validateOwner(@PathVariable("userId") Long userId);

    @GetMapping("/users/internal/id-by-email")
    UserIdResponseDTO getUserIdByEmail(@RequestParam String email);

}
