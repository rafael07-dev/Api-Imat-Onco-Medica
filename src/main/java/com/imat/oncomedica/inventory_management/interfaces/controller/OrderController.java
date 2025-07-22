package com.imat.oncomedica.inventory_management.interfaces.controller;

import com.imat.oncomedica.inventory_management.application.dto.order.OrderResponse;
import com.imat.oncomedica.inventory_management.application.usecase.order.GetAllOrdersUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.order.GetOrderByIdUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.order.GetOrderByStaffIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final GetOrderByStaffIdUseCase getOrderByStaffIdUseCase;

    public OrderController(GetAllOrdersUseCase getAllOrdersUseCase, GetOrderByIdUseCase getOrderByIdUseCase, GetOrderByStaffIdUseCase getOrderByStaffIdUseCase) {
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.getOrderByStaffIdUseCase = getOrderByStaffIdUseCase;
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return ResponseEntity.ok()
                .body(getAllOrdersUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok()
                .body(getOrderByIdUseCase.execute(id));
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<List<OrderResponse>> getOrderByStaffId(@PathVariable Integer id){
        return ResponseEntity.ok()
                .body(getOrderByStaffIdUseCase.execute(id));
    }
}
