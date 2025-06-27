package com.imat.oncomedica.inventory_management.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentResponse {

    private Integer id;
    private String equipmentName;
    private String imageUrl;
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
