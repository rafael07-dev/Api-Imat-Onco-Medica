package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UpdateMaintenanceRequest {

    private String inventoryCode;
    private String maintenanceStaffName;
    private String activity;
    private Date scheduledDate;
    private Date startDate;
    private Date startTime;
    private Date deliveryDate;
    private Date deliveryTime;
    private Date timeUsed;
    private Date registrationDate;
    private Date dateOfCompletion;
    private String observations;
    private String staffObservations;
    private boolean done;
}
