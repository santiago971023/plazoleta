package com.mycompany.app.microservice_restaurant_catalog.application.ports.in;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateProductRequestDTO;

public interface CreateProductUseCase {

    void createProduct(CreateProductRequestDTO requestDTO, Long autheticatedUserId);

}
