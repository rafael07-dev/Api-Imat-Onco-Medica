package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateMaintenanceScheduleRequest {
    private Integer equipmentId;
    private Integer maintenanceStaffId;
    private List<CreateMonthlyMaintenanceRequest> months;
}
