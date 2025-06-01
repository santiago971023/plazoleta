package com.microservice_order.application.ports.in;

import com.microservice_order.application.DTOs.CreateOrderDTO;
import com.microservice_order.domain.model.Order;

public interface CreateOrderUseCase {

    Order createOrder(CreateOrderDTO createOrderDTO);

}
