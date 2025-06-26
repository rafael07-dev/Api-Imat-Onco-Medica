package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonIgnore
    private MaintenanceSchedule maintenanceSchedule;

    private Integer month;

    private Integer year;

    @OneToMany(mappedBy = "monthlyMaintenance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyMaintenanceType> maintenanceTypes;

}
