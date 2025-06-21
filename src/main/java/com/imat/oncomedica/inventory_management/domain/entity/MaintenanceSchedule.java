package com.imat.oncomedica.inventory_management.domain.entity;

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
public class MaintenanceSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private MaintenanceStaff responsible;

    @OneToMany(mappedBy = "maintenanceSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyMaintenance> monthlyMaintenances;

}
