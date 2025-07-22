package com.imat.oncomedica.inventory_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenanceType {

    private int id;
    private MaintenanceTypeEnum maintenanceTypeEnum;
    private MonthlyMaintenance monthlyMaintenance;
    private Integer quantity;
}
