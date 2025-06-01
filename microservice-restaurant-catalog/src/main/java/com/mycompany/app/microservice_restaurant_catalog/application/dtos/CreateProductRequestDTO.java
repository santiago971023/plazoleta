package com.mycompany.app.microservice_restaurant_catalog.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDTO {

    @NotBlank(message = "El nombre del plato es obligatorio")
    private String name;

    @NotNull(message = "El precio del plato es obligatorio")
    @Positive(message = "El precio del plato debe ser un número positivo y mayor a 0")
    private Double price;

    @NotBlank(message = "La descripción del plato es obligatoria")
    private String description;

    @NotBlank(message = "La URL de la imagen del plato es obligatoria")
    private String urlImage;

    @NotBlank(message = "La categoría del plato es obligatoria")
    private String category; // Representado como String aquí, validado/convertido a CategoryEnum en el servicio

    @NotNull(message = "El ID del restaurante es obligatorio")
    @Positive(message = "El ID del restaurante debe ser un número positivo")
    private Long restaurantId; // Se necesita para asociar el producto al restaurante
}


