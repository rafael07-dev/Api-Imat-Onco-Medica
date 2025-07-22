package com.imat.oncomedica.inventory_management.application.usecase.order;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.application.mapper.OrderMapper;
import com.imat.oncomedica.inventory_management.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetAllOrdersUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public GetAllOrdersUseCase(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderResponse> execute() {
        return orderRepository.getAllOrders()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }
}
