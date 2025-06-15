package com.imat.oncomedica.inventory_management.application.dto;

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
    private String name;
    private String lastName;
    private Date scheduledDate;
    private Date startDate;
    private Date startTime;
    private Date deliveryDate;
    private Date timeUsed;
    private Date registrationDate;
    private Date dateOfCompletion;
    private String type;
    private String observations;

}
