package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateMaintenanceRequest {

    private Integer equipmentId;
    private Integer staffId;
    private LocalDateTime scheduledDate;
    private LocalDateTime startDate;
    private LocalDateTime deliveryDate;
    private LocalDateTime registrationDate;
    private String typeMaintenance;
    private String observations;
    private boolean done;
}
