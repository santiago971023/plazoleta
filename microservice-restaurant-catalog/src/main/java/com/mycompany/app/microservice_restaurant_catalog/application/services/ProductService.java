package com.mycompany.app.microservice_restaurant_catalog.application.services;

import com.mycompany.app.microservice_restaurant_catalog.application.dtos.CreateProductRequestDTO;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.ErrorMessagesApplication;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.NoDataFoundException;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.OwnerNotFoundOrInvalidRoleException;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.in.CreateProductUseCase;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.ProductRepositoryPort;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.RestaurantRepositoryPort;
import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.UserValidationPort;
import com.mycompany.app.microservice_restaurant_catalog.domain.CategoryEnum;
import com.mycompany.app.microservice_restaurant_catalog.domain.Product;
import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase {

    private final RestaurantRepositoryPort restaurantRepositoryPort;
    private final UserValidationPort userValidationPort;
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public void createProduct(CreateProductRequestDTO requestDTO, Long authenticatedUserId) {

        // Validar que el restaurante que sirva el plato, existe
        Restaurant existingRestaurant = restaurantRepositoryPort.findById(requestDTO.getRestaurantId())
                .orElseThrow( () -> new NoDataFoundException("Restaurant not found with ID: " + requestDTO.getRestaurantId()));


        // Valido que el role del usuario autenticado sea válido y que el id corresponda al restaurante dueño del plato
        if(!userValidationPort.isValidOwner(authenticatedUserId)){
            throw new OwnerNotFoundOrInvalidRoleException(ErrorMessagesApplication.OWNER_NOT_FOUND_OR_ROLED_INVALID.getMessage());
        }

        if(!authenticatedUserId.equals(existingRestaurant.getOwnerId())){
            throw new OwnerNotFoundOrInvalidRoleException(ErrorMessagesApplication.OWNER_NOT_FOUND_OR_ROLED_INVALID.getMessage());
        }

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setUrlImage(requestDTO.getImageUrl());
        product.setRestaurantId(requestDTO.getRestaurantId());

        try{

            product.setCategory(CategoryEnum.valueOf(requestDTO.getCategory().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new NoDataFoundException("Category not fund or is invalid");
        }

        product.setActive(true);
        productRepositoryPort.save(product);


    }
}
