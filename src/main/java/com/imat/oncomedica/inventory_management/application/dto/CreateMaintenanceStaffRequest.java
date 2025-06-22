package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;

@Data
public class CreateMaintenanceStaffRequest {

    private String firstName;
    private String lastName;
    private String occupation;
    private String email;
    private boolean availability;
}
