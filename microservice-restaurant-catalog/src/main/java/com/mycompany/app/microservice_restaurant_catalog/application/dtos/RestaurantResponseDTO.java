package com.mycompany.app.microservice_restaurant_catalog.application.dtos;

import lombok.*;

@Data // Proporciona Getter, Setter, etc.
@NoArgsConstructor
public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private String nit;
    private String phone;
    private String address;
    private String urlLogo;

}
