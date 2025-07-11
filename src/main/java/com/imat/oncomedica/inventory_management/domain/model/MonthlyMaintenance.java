package com.imat.oncomedica.inventory_management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenance implements Serializable {

    private Integer id;

    @JsonIgnore
    private MaintenanceSchedule maintenanceSchedule;

    private Integer month;

    private Integer year;

    private List<MonthlyMaintenanceType> maintenanceTypes;

}
