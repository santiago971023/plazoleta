package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.adapters;

import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.ProductRepositoryPort;
import com.mycompany.app.microservice_restaurant_catalog.domain.Product;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities.ProductEntity;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities.RestaurantEntity;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.mapper.ProductMapper;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    @Override
    public void save(Product product) {
        ProductEntity productEntity = productMapper.toProductEntity(product);
        productJpaRepository.save(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productJpaRepository.findById(id);
        return optionalProductEntity.map(productMapper::toProduct);
    }

    @Override
    public List<Product> findAllByRestaurantId(Long restaurantId) {
        List<ProductEntity> productEntities = productJpaRepository.findByRestaurantId(restaurantId);
        return productMapper.toProductList(productEntities);
    }

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
}
