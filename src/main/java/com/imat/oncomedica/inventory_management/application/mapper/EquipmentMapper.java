package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.EquipmentResponse;
import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    default Equipment toEquipment(CreateEquipmentRequest equipment){
        return new Equipment(
                null,
                equipment.getEquipmentName(),
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
                null
        );
    }
    default EquipmentResponse toEquipmentResponse(Equipment equipment){
        return new EquipmentResponse(
                equipment.getId(),
                equipment.getEquipmentName(),
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
    List<EquipmentResponse> toEquipmentResponseList(List<Equipment> equipments);
}
