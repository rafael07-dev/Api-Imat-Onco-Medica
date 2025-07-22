package com.imat.oncomedica.inventory_management.application.usecase.order;

import com.imat.oncomedica.inventory_management.domain.model.*;
import com.imat.oncomedica.inventory_management.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void execute(Maintenance maintenance, Equipment equipment, MaintenanceStaff staff){
        Order order = new Order();
        order.setCreationDate(LocalDateTime.now());
        order.setEquipment(equipment);
        order.setMaintenance(maintenance);
        order.setMaintenanceStaff(staff);

        if (maintenance.isDone()){
            order.setStatus(OrderStatus.COMPLETED);
        }else {
            order.setStatus(OrderStatus.PENDING);
        }

        orderRepository.saveOrder(order);
    }
}
