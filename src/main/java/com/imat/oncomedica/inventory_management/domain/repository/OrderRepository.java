package com.imat.oncomedica.inventory_management.domain.repository;

import com.imat.oncomedica.inventory_management.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> getAllOrders();
    Optional<Order> getOrderById(Integer id);
    List<Order> getOrdersByStaffId(Integer id);
    Order saveOrder(Order order);
    Optional<Order> getOrderByMaintenanceId(Integer id);
}
