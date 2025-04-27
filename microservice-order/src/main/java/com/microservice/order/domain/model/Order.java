package com.microservice.order.domain.model;


import java.util.Date;


public class Order {

    private Long id;

    private Status status;

    private String clientId;

    private Long restaurantId;

    private Date dateCreation;

    public Order(Long id, Status status, String clientId, Long restaurantId, Date dateCreation) {
        this.id = id;
        this.status = status;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dateCreation = dateCreation;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
