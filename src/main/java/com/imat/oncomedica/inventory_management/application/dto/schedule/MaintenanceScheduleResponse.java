package com.imat.oncomedica.inventory_management.application.dto;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
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
    private List<MonthlyMaintenance> monthlyMaintenances;
}