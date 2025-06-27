package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MaintenanceTypeEnum maintenanceTypeEnum;

    @ManyToOne
    @JoinColumn(name = "monthly_maintenance_id")
    @JsonIgnore
    private MonthlyMaintenance monthlyMaintenance;

    private Integer quantity;
}
