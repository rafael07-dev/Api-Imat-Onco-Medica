package com.imat.oncomedica.inventory_management.application.dto.equipment;

import lombok.Data;

@Data
public class CreateEquipmentRequest {

    private Integer id;
    private String equipmentName;
    private String type;
    private String inventoryCode;
    private String brand;
    private String model;
    private String series;
    private String location;
    private String area;
    private String frequency;
    private String floor;
    private String tower;
}
