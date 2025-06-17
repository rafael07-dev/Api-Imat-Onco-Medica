package com.imat.oncomedica.inventory_management.application.dto;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import lombok.Data;
import java.util.List;

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
    private List<MonthlyMaintenance> monthlyMaintenances;

    public MaintenanceScheduleResponse(MaintenanceSchedule maintenanceSchedule) {
        this.id = maintenanceSchedule.getId();
        this.equipmentId = maintenanceSchedule.getEquipment().getId();
        /*this.equipmentName = maintenanceSchedule.getEquipmentName();
        this.inventoryCode = maintenanceSchedule.getInventoryCode();
        this.brand = maintenanceSchedule.getBrand();
        this.model = maintenanceSchedule.getModel();
        this.location = maintenanceSchedule.getLocation();
        this.type = maintenanceSchedule.getType();
        this.floor = maintenanceSchedule.getFloor();
        this.tower = maintenanceSchedule.getTower();
        this.area = maintenanceSchedule.getArea();*/
        this.monthlyMaintenances = maintenanceSchedule.getMonthlyMaintenances();
    }
}