package com.imat.oncomedica.inventory_management.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {

    private Integer id;
    private LocalDateTime creationDate;
    private MaintenanceStaff maintenanceStaff;
    private Equipment equipment;
    private Maintenance maintenance;
    private OrderStatus status;
}
