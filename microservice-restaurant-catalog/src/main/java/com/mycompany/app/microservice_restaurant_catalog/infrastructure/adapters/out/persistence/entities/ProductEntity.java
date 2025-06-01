package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "image_url", nullable = false) // Campo para la URL de la imagen
    private String urlImage;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
