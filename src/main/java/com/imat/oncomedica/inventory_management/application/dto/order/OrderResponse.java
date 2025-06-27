package com.imat.oncomedica.inventory_management.application.dto;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.entity.OrderStatus;
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
