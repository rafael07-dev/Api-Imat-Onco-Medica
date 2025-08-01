package com.imat.oncomedica.inventory_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    private Long id;

    private Equipment equipment;

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
