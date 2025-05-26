package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.mapper;

import com.mycompany.app.microservice_restaurant_catalog.domain.Product;
import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities.ProductEntity;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {


    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);

    // Metodos para mapear listas, ej:
    List<Product> toProductList(List<ProductEntity> productEntityList);
    List<ProductEntity> toProductEntityList(List<Product> domainProductList);

}
