package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MonthlyMaintenanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "maintenance_type_id")
    @JsonIgnore
    private MaintenanceType maintenanceType;

    @ManyToOne
    @JoinColumn(name = "monthly_maintenance_id")
    @JsonIgnore
    private MonthlyMaintenance monthlyMaintenance;

    private Integer quantity;
}
