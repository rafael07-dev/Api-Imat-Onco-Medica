package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenanceType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MonthlyMaintenanceTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MaintenanceTypeEnum maintenanceTypeEnum;

    @ManyToOne
    @JoinColumn(name = "monthly_maintenance_id")
    @JsonIgnore
    private MonthlyMaintenanceType monthlyMaintenance;

    private Integer quantity;
}
