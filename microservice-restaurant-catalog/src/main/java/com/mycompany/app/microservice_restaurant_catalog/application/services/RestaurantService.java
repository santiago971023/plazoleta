package com.mycompany.app.microservice_restaurant_catalog.application.services;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateRestaurantRequestDTO;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.ErrorMessagesApplication;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.NitAlreadyExistsException;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.RestaurantNameAlreadyExistsException;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.in.CreateRestaurantUseCase;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.RestaurantRepositoryPort;
import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService implements CreateRestaurantUseCase {

    private final RestaurantRepositoryPort restaurantRepositoryPort;

    // Este servicio NECESITARA saber quien es el usuario autenticado para validar el rol (PROPIETARIO)
    // Necesitaremos alguna forma de acceder a la info del user autenticado desde este microservicio.
    // Esto requiere comunicacion entre microservicios (RestaurantService llamando a User Microservice). Lo abordaremos despues.
    // Por ahora, ignora la validacion del rol del propietario autenticado.
    // Si tuvieras AuthenticatedUserPort AQUI (en este microservicio), lo inyectarias asi:
    // private final AuthenticatedUserPort authenticatedUserPort; // <-- Esto seria AQUI si el puerto estuviera en este microservicio

    public RestaurantService(RestaurantRepositoryPort restaurantRepositoryPort) {
        this.restaurantRepositoryPort = restaurantRepositoryPort;
    }

    @Override
    public void createRestaurant(CreateRestaurantRequestDTO createRestaurantRequestDTO) {

        // Validaciones de existencia, los restaurantes deben tener nombre único y nit único
        if(restaurantRepositoryPort.existsByName(createRestaurantRequestDTO.getName())){
            throw new RestaurantNameAlreadyExistsException(ErrorMessagesApplication.NAME_ALREADY_EXIST.getMessage());
        }

        if(restaurantRepositoryPort.existsByNit(createRestaurantRequestDTO.getNit())){
            throw new NitAlreadyExistsException(ErrorMessagesApplication.NIT_ALREADY_EXIST.getMessage());
        }

        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(createRestaurantRequestDTO.getName());
        newRestaurant.setAddress(createRestaurantRequestDTO.getAddress());
        newRestaurant.setNit(createRestaurantRequestDTO.getNit());
        newRestaurant.setPhone(createRestaurantRequestDTO.getPhone());
        newRestaurant.setUrlLogo(createRestaurantRequestDTO.getUrlLogo());
        newRestaurant.setOwnerId(createRestaurantRequestDTO.getOwnerId());

        restaurantRepositoryPort.save(newRestaurant);
    }
}
