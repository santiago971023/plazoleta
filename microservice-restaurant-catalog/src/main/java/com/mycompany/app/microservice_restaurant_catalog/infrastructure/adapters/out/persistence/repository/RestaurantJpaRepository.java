package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.repository;

import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long>  {
    boolean existsByNit(String nit);
    boolean existsByName(String name);
}
