package com.mycompany.app.microservice_restaurant_catalog.domain;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String urlImage;
    private CategoryEnum category;
    private Long restaurantId;
    private boolean active;

    public Product(Long id, String name, String description, BigDecimal price, String urlImage, CategoryEnum category, Long restaurantId, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.category = category;
        this.restaurantId = restaurantId;
        this.active = active;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
