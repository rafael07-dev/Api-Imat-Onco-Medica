package com.imat.oncomedica.inventory_management.application.dto.schedule;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MonthlyMaintenanceResponse;
import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceScheduleResponse {
    private Integer id;
    private Equipment equipment;
    private MaintenanceStaff responsible;
    private List<MonthlyMaintenanceResponse> monthlyMaintenances;
}