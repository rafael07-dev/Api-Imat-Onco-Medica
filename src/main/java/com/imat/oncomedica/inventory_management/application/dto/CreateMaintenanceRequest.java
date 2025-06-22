package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateMaintenanceRequest {

    private Integer equipmentId;
    private Integer staffId;
    private Date scheduledDate;
    private Date startDate;
    private Date deliveryDate;
    private Date registrationDate;
    private String typeMaintenance;
    private String observations;
}
