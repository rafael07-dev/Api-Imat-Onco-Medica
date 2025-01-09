package com.imat.oncomedica.inventory_management.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MonthlyMaintenanceDTO {

    private String month;
    private Map<String, Integer> maintenanceType;
}
