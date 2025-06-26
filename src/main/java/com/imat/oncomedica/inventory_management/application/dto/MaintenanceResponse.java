package com.imat.oncomedica.inventory_management.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MaintenanceResponse {

    private Long id;
    private String inventoryCode;
    private String maintenanceStaffName;
    private Integer maintenancesNumber;
    private String activity;
    private LocalDateTime scheduledDate;
    private LocalDateTime startDate;
    private LocalDateTime startTime;
    private LocalDateTime deliveryDate;
    private LocalDateTime timeUsed;
    private LocalDateTime registrationDate;
    private LocalDateTime dateOfCompletion;
    private String observations;
    private String staffObservations;
    private boolean done;
}
