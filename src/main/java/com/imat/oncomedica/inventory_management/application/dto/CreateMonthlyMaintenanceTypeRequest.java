package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;

@Data
public class CreateMonthlyMaintenanceTypeRequest {

    private CreateMaintenanceTypeRequest maintenanceType;
    private Integer quantity;
}
