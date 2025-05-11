package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
public class RestaurantEntity {

    @Id // <--- Anotacion de JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generacion de ID
    @Column(name = "restaurant_id") // Nombre de la columna en la BD
    private Long id;

    @Column(name = "name", nullable = false, unique = true) // Unico a nivel BD si la regla lo requiere
    private String name;

    @Column(name = "nit", nullable = false, unique = true) // Unico a nivel BD
    private String nit;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "url_logo", nullable = false)
    private String urlLogo;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId; // ID del propietario

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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
