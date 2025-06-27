package com.imat.oncomedica.inventory_management.application.dto.maintenance;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyMaintenanceResponse {

    private Integer month;
    private Integer year;

    List<MonthlyMaintenanceTypeResponse> maintenances;
}
