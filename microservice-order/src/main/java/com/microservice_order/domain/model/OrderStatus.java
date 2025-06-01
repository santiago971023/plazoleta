package com.microservice_order.domain.model;

public enum OrderStatus {
    PENDING("Pendiente"),
    PREPARING("En preparación"),
    READY("Lista para recoger"),
    DELIVERED("Entregada"),
    CANCELLED("Cancelada");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
