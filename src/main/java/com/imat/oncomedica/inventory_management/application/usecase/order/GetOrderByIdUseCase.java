package com.imat.oncomedica.inventory_management.application.usecase.order;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.application.mapper.OrderMapper;
import com.imat.oncomedica.inventory_management.domain.exception.OrderNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public GetOrderByIdUseCase(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponse execute(Integer id) {
        return orderRepository.getOrderById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
