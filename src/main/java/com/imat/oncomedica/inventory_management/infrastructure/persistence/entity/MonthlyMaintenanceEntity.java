package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class MonthlyMaintenanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonIgnore
    private MaintenanceScheduleEntity maintenanceSchedule;

    private Integer month;

    private Integer year;

    @OneToMany(mappedBy = "monthlyMaintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyMaintenanceTypeEntity> maintenanceTypes;
}
