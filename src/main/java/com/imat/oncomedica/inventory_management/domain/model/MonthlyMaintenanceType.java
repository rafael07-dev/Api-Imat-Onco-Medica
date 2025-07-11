package com.imat.oncomedica.inventory_management.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenanceType {

    private int id;

    @Enumerated(EnumType.STRING)
    private MaintenanceTypeEnum maintenanceTypeEnum;

    @JsonIgnore
    private MonthlyMaintenance monthlyMaintenance;

    private Integer quantity;
}
