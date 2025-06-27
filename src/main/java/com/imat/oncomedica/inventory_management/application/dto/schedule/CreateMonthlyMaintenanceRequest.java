package com.imat.oncomedica.inventory_management.application.dto.maintenance;

import lombok.Data;
import java.util.List;

@Data
public class CreateMonthlyMaintenanceRequest {

    private Integer month;
    private int year;
    private List<CreateMonthlyMaintenanceTypeRequest> maintenanceType;
}
