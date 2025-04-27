package com.microservice.order.domain.api;

import com.microservice.order.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    List<Order> getOrdersByClientId(String clientId);

}
