package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "mantenimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff maintenanceStaff;

    private LocalDateTime scheduledDate;

    private LocalDateTime startDate;

    private LocalDateTime startTime;

    private LocalDateTime deliveryDate;

    private LocalDateTime deliveryTime;

    private LocalDateTime timeUsed;

    private LocalDateTime registrationDate;

    private LocalDateTime dateOfCompletion;

    private String type;

    private String observations;

    private String staffObservations;
    private boolean done;

    private String evidenceImg;

}
