package com.imat.oncomedica.inventory_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceSchedule implements Serializable {

    private Integer id;

    private Equipment equipment;

    private MaintenanceStaff responsible;

    private List<MonthlyMaintenance> monthlyMaintenances;

}
