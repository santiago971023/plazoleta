package com.microservice_order.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrderStatusHistory {
    private Long id;
    private Long orderId;
    private OrderStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Long durationInMinutes;

    public OrderStatusHistory() {
    }

    public OrderStatusHistory(Long id, Long orderId, OrderStatus status, LocalDateTime startedAt, LocalDateTime endedAt,
                              Long durationInMinutes) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.durationInMinutes = durationInMinutes;
    }

    public OrderStatusHistory(Long orderId, OrderStatus status, LocalDateTime startedAt) {
        this.orderId = orderId;
        this.status = status;
        this.startedAt = startedAt;
    }

    public void calculateAndSetDurationInMinutes() {
        if (startedAt != null && endedAt != null) {
            durationInMinutes = Duration.between(startedAt, endedAt).toMinutes();
        }
        // TODO: throw exception if startedAt or endedAt is null
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
