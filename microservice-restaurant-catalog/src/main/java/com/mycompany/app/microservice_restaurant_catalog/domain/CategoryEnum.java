package com.mycompany.app.microservice_restaurant_catalog.domain;

public enum CategoryEnum {
    ENTRADA("Entrada"),
    PLATO_FUERTE("Plato Fuerte"),
    BEBIDA("Bebida"),
    POSTRE("Postre");

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    CategoryEnum(String displayName) {
        this.displayName = displayName;
    }
}
