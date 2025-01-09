package com.imat.oncomedica.inventory_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {

    private Long id;
    private Integer equipmentId;
    private Integer maintenanceStaffId;
    private Date scheduledDate;
    private Date dateOfCompletion;
    private String type;
    private String observations;

}
