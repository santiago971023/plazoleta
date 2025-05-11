package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.mapper;

import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    // Método para convertir un objeto de tipo Restaurant (de dominio) a un objeto de tipo RestaurantEntity
    RestaurantEntity toRestaurantEntity(Restaurant domainRestaurant);

    // Método para convertir RestaurantEntity a un objeto Restaurant (de dominio)
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    // Metodos para mapear listas, ej:
    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntityList);
    List<RestaurantEntity> toRestaurantEntityList(List<Restaurant> domainRestaurantList);

}
