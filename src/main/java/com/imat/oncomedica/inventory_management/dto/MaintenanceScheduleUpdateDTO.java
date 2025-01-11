package com.imat.oncomedica.inventory_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class MaintenanceScheduleUpdateDTO {

    private Integer equipmentId;
    private String equipmentName;
    private String inventoryCode;
    private String brand;
    private String model;
    private String location;
    private String type;
    private String floor;
    private String tower;
    private String area;

    private List<MonthlyMaintenanceDTO> monthlyMaintenances;
}
