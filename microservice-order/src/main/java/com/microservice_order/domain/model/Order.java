package com.microservice_order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private Long id;
    private Long clientId;
    private Long restaurantId;
    private Long assignedEmployeeId;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItem> items;
    private List<OrderStatusHistory> statusHistory;
    private BigDecimal totalPrice;
    private String pickupPin;
    private String notes;

    public Order() {
    }

    public Order(Long id, Long clientId, Long restaurantId, Long assignedEmployeeId, LocalDateTime createdAt, OrderStatus status, List<OrderItem> items, List<OrderStatusHistory> statusHistory, BigDecimal totalPrice, String pickupPin, String notes) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.assignedEmployeeId = assignedEmployeeId;
        this.createdAt = createdAt;
        this.status = status;
        this.items = items;
        this.statusHistory = statusHistory;
        this.totalPrice = totalPrice;
        this.pickupPin = pickupPin;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Long assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderStatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<OrderStatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPickupPin() {
        return pickupPin;
    }

    public void setPickupPin(String pickupPin) {
        this.pickupPin = pickupPin;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
