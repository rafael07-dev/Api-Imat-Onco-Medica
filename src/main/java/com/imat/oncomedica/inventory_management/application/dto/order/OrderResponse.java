package com.imat.oncomedica.inventory_management.application.dto.order;

import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.model.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Integer id;
    private LocalDateTime creationDate;
    private MaintenanceStaff maintenanceStaff;
    private Equipment equipment;
    private OrderStatus status;
}
