package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    default Equipment toEquipment(EquipmentRequest equipment){
        return new Equipment(
                null,
                equipment.getEquipmentName(),
                null,
                equipment.getType(),
                equipment.getInventoryCode(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getSeries(),
                equipment.getLocation(),
                equipment.getArea(),
                equipment.getFrequency(),
                equipment.getFloor(),
                equipment.getTower(),
                null,
                null,
                null
        );
    }
    default EquipmentResponse toEquipmentResponse(Equipment equipment){
        return new EquipmentResponse(
                equipment.getId(),
                equipment.getEquipmentName(),
                equipment.getImageUrl(),
                equipment.getType(),
                equipment.getInventoryCode(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getSeries(),
                equipment.getLocation(),
                equipment.getArea(),
                equipment.getFrequency(),
                equipment.getFloor(),
                equipment.getTower()
        );
    };
}
