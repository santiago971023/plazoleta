package com.microservice_order.application.services;

import com.microservice_order.application.DTOs.CreateOrderDTO;
import com.microservice_order.application.ports.in.CreateOrderUseCase;
import com.microservice_order.domain.model.Order;

public class CreateOrderService implements CreateOrderUseCase {
    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO) {
        return null;
    }
}
