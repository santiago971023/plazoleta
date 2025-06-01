package com.microservice_order.application.ports.out;

import com.microservice_order.domain.model.Order;

public interface OrderRepositoryPort {

    Order save(Order order);

}
