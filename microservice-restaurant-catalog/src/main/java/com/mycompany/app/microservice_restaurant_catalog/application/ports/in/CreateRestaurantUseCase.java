package com.mycompany.app.microservice_restaurant_catalog.application.ports.in;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateRestaurantRequestDTO;

public interface CreateRestaurantUseCase {


    void createRestaurant(CreateRestaurantRequestDTO createRestaurantRequestDTO);


}


