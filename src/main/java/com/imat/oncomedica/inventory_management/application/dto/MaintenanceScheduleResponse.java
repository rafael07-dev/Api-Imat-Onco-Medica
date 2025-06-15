package com.imat.oncomedica.inventory_management.application.dto;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import lombok.Data;
import java.util.Map;

@Data
public class MaintenanceScheduleResponse {
    private Integer id;
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
    private Integer monthlyMaintenanceId;
    private Map<String, Integer> maintenanceType;
    private String month;

    public MaintenanceScheduleResponse(MaintenanceSchedule maintenanceSchedule) {
        this.id = maintenanceSchedule.getId();
        this.equipmentId = maintenanceSchedule.getEquipmentId();
        this.monthlyMaintenanceId = maintenanceSchedule.getMonthlyMaintenanceId();
        this.equipmentName = maintenanceSchedule.getEquipmentName();
        this.inventoryCode = maintenanceSchedule.getInventoryCode();
        this.brand = maintenanceSchedule.getBrand();
        this.model = maintenanceSchedule.getModel();
        this.location = maintenanceSchedule.getLocation();
        this.type = maintenanceSchedule.getType();
        this.floor = maintenanceSchedule.getFloor();
        this.tower = maintenanceSchedule.getTower();
        this.area = maintenanceSchedule.getArea();
        this.maintenanceType = maintenanceSchedule.getMaintenanceType();
        this.month = maintenanceSchedule.getMonth();
    }
}