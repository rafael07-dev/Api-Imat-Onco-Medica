package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;

@Data
public class UpdateMaintenanceStaffRequest {

    private Integer id;
    private String firstName;
    private String lastName;
    private String occupation;
    private boolean availability; // "available" or "busy"
    private Integer maintenanceCompleted;
}
