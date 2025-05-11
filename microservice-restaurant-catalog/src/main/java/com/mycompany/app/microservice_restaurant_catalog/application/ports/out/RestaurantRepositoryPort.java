package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepositoryPort {


    void save(Restaurant restaurant); // Guardar un nuevo restaurante o actualizar uno existente
    Optional<Restaurant> findById(Long id); // Encontrar un restaurante por su ID
    List<Restaurant> findAll(); // Obtener todos los restaurantes (puede necesitar paginacion despues)
    void deleteById(Long id); // Eliminar un restaurante por su ID
    boolean existsByNit(String nit);
    boolean existsByName(String name);

}
