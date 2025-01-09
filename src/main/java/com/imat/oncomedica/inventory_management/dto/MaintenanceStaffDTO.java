package com.imat.oncomedica.inventory_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceStaffDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String ocupation;
    private boolean availability;
    private Integer maintenanceCompleted;
}
