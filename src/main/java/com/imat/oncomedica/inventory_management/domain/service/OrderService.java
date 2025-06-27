package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Integer id);
    List<OrderResponse> getOrdersByStaffId(Integer id);
}
