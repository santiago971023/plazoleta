package com.microservice_order.application.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    @Positive(message = "El ID del producto debe ser un valor positivo")
    private Long productId;

    @NotNull(message = "La cantidad del producto es obligatoria")
    @Min(value = 1, message = "La cantidad del producto debe ser al menos 1")
    private Integer quantity;


}
