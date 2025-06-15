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

    @OneToMany(mappedBy = "maintenanceSchedule")
    private List<MonthlyMaintenance> monthlyMaintenances;

    // Detalles del equipo (redundantes para facilitar el acceso)
    private String equipmentName;
    private String inventoryCode;
    private String brand;
    private String model;
    private String location;
    private String type;
    private String floor;
    private String tower;
    private String area;
}
