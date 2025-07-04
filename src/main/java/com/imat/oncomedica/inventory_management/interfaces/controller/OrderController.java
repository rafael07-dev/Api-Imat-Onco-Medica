package com.imat.oncomedica.inventory_management.interfaces.controller;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.domain.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity.ok()
                .body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok()
                .body(orderService.getOrderById(id));
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<List<OrderResponse>> getOrderByStaffId(@PathVariable Integer id){
        return ResponseEntity.ok()
                .body(orderService.getOrdersByStaffId(id));
    }
}
