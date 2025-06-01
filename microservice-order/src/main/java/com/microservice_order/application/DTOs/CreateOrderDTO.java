package com.microservice_order.application.DTOs;

import com.microservice_order.domain.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {

    @NotNull(message = "El ID del restaurante es obligatorio")
    @Positive(message = "El ID del restaurante debe ser un valor positivo")
    private Long restaurantId;

    @NotEmpty(message = "La orden debe contener al menos un plato")
    @Valid
    private List<OrderItem> items;


    private String notes;

}
