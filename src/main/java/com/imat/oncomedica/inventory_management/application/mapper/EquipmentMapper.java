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
    default List<EquipmentResponse> toEquipmentResponseList(List<Equipment> equipments){
        return equipments.stream()
                .map(this::toEquipmentResponse)
                .toList();
    };
}
