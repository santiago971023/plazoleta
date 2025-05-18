package com.mycompany.app.microservice_restaurant_catalog.infrastructure.feign.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class OwnerValidationResponseDTO {

    private boolean isValidOwner;

    public boolean isValidOwner() {
        return isValidOwner;
    }

    public void setValidOwner(boolean validOwner) {
        isValidOwner = validOwner;
    }
}