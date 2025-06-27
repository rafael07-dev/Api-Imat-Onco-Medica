package com.imat.oncomedica.inventory_management.application.dto.maintenance;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateMaintenanceRequest {

    private String inventoryCode;
    private String maintenanceStaffName;
    private String activity;
    private LocalDateTime scheduledDate;
    private LocalDateTime startDate;
    private LocalDateTime startTime;
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveryTime;
    private LocalDateTime timeUsed;
    private LocalDateTime registrationDate;
    private LocalDateTime dateOfCompletion;
    private String observations;
    private String staffObservations;
    private boolean done;
}
