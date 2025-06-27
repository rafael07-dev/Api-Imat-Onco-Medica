package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.application.mapper.OrderMapper;
import com.imat.oncomedica.inventory_management.domain.entity.Order;
import com.imat.oncomedica.inventory_management.domain.exception.OrderNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.OrderService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        var orders = orderRepository.findAll();

        return orders.stream()
                .map(o -> orderMapper.toOrderResponse(o))
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByStaffId(Integer id) {
        List<Order> order = orderRepository.findByMaintenanceStaff_Id(id);

        if (order.isEmpty()) throw new OrderNotFoundException(id);

        return order.stream()
                .map(o -> orderMapper.toOrderResponse(o))
                .toList();
    }
}
