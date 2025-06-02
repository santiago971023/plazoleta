package com.microservice_order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private Long clientId;
    private String clientName;
    private Long restaurantId;
    private String restaurantName;
    private Long assignedEmployeeId;
    private String employeeName;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private List<OrderItem> items;
    private List<OrderStatusHistory> statusHistory;
    private BigDecimal totalPrice;
    private String pickupPin;
    private String notes;

    public Order() {
    }

    public Order(Long id, Long clientId, String clientName, Long restaurantId, String restaurantName,
                 Long assignedEmployeeId, String employeeName, LocalDateTime createdAt, OrderStatus status,
                 List<OrderItem> items, List<OrderStatusHistory> statusHistory, BigDecimal totalPrice, String pickupPin,
                 String notes) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.assignedEmployeeId = assignedEmployeeId;
        this.employeeName = employeeName;
        this.createdAt = createdAt;
        this.status = status;
        this.items = items;
        this.statusHistory = statusHistory;
        this.totalPrice = totalPrice;
        this.pickupPin = pickupPin;
        this.notes = notes;
    }

    public void calculateAndSetTotalPrice() {
        totalPrice = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItem item : items) {
                totalPrice = totalPrice.add(item.getSubtotal());
            }
        }
        // TODO: lanzar excepción en else block si llega a ser necesario
    }

    public void addOrderItem(OrderItem item) {
        // TODO: deberíamos validar que ni el item ni el listado de items sean nulos
        items.add(item);
    }

    public void deleteOrderItem(Long itemId) {
        // TODO: también deberíamos validar excepciones aquí
        items.removeIf(item -> itemId.equals(item.getId()));
    }

    // TODO: creo que este método si es necesario. Tendríamos que revisar si sería necesario crear el de eliminar
    public void addStatusHistory(OrderStatusHistory statusHistory) {
        // TODO: también deberíamos validar excepciones aquí
        this.statusHistory.add(statusHistory);
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Long assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
