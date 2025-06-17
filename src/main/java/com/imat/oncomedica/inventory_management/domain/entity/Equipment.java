package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "equipos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String equipmentName;
    private String imageUrl;
    private String type;
    private String inventoryCode;
    private String brand;
    private String model;
    private String series;
    private String location;
    private String area;
    private String frequency;
    private String floor;
    private String tower;

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<MaintenanceSchedule> maintenanceSchedules;
}
