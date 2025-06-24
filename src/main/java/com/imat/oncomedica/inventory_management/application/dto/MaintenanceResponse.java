package com.imat.oncomedica.inventory_management.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MaintenanceResponse {

    private Long id;
    private String inventoryCode;
    private String maintenanceStaffName;
    private Integer maintenancesNumber;
    private String activity;
    private Date scheduledDate;
    private Date startDate;
    private Date startTime;
    private Date deliveryDate;
    private Date timeUsed;
    private Date registrationDate;
    private Date dateOfCompletion;
    private String observations;
    private String staffObservations;
    private boolean done;
}
