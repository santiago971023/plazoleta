package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import com.mycompany.app.microservice_restaurant_catalog.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Puerto de salida
@Repository
public interface ProductRepositoryPort {

    void save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAllByRestaurantId(Long restaurantId);
    void deleteById(Long id);

}
