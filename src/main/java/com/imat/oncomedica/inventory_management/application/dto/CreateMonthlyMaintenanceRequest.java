package com.imat.oncomedica.inventory_management.application.dto;

import lombok.Data;
import java.util.Map;

@Data
public class CreateMonthlyMaintenanceRequest {

    private String month;
    private Map<String, Integer> maintenanceType;
}
