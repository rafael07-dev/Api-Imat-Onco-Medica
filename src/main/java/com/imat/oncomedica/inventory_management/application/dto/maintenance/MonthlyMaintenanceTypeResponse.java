package com.imat.oncomedica.inventory_management.application.dto.maintenance;

import lombok.Data;

@Data
public class MonthlyMaintenanceTypeResponse {
    private String maintenanceType;
    private Integer quantity;
}
