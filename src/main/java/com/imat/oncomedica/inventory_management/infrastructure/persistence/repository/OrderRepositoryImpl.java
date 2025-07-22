package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.Order;
import com.imat.oncomedica.inventory_management.domain.repository.OrderRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.OrderPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final SpringDataOrderRepository orderRepository;
    private final OrderPersistenceMapper orderMapper;

    public OrderRepositoryImpl(SpringDataOrderRepository orderRepository, OrderPersistenceMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::toModel);
    }

    @Override
    public List<Order> getOrdersByStaffId(Integer id) {
        return orderRepository.findByMaintenanceStaff_Id(id)
                .stream()
                .map(orderMapper::toModel)
                .toList();
    }

    @Override
    public Order saveOrder(Order order) {
        var orderEntity = orderMapper.toOrderEntity(order);
        var orderSaved = orderRepository.save(orderEntity);

        return orderMapper.toModel(orderSaved);
    }

    @Override
    public Optional<Order> getOrderByMaintenanceId(Integer id) {
        return orderRepository.findByMaintenance_Id(id)
                .map(orderMapper::toModel);
    }
}
