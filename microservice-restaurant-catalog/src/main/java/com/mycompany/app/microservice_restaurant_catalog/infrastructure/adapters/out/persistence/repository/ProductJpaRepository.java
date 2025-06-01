package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.repository;

import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByRestaurantId(Long restaurantId);
}
