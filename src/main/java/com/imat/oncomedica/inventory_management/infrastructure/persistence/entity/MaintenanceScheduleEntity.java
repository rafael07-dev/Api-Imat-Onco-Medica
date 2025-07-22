package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cronograma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceScheduleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private MaintenanceStaffEntity responsible;

    @OneToMany(mappedBy = "maintenanceSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyMaintenanceEntity> monthlyMaintenances;

}
