package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence;

import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.RestaurantRepositoryPort;
import com.mycompany.app.microservice_restaurant_catalog.domain.Restaurant;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.mapper.RestaurantMapper;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.repository.RestaurantJpaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements RestaurantRepositoryPort {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantJpaAdapter(RestaurantJpaRepository restaurantJpaRepository, RestaurantMapper restaurantMapper) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.restaurantMapper = restaurantMapper;
    }


    @Override
    public void save(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantMapper.toRestaurantEntity(restaurant);
        restaurantJpaRepository.save(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        Optional<RestaurantEntity> restaurantEntityOptional = restaurantJpaRepository.findById(id);
        return restaurantEntityOptional.map(restaurantMapper::toRestaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        List<RestaurantEntity> restaurantEntities = restaurantJpaRepository.findAll();
        return restaurantMapper.toRestaurantList(restaurantEntities);
    }

    @Override
    public void deleteById(Long id) {
        restaurantJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByNit(String nit) {
        return restaurantJpaRepository.existsByNit(nit);
    }

    @Override
    public boolean existsByName(String name) {
        return restaurantJpaRepository.existsByName(name);
    }
}
