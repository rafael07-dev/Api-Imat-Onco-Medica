package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.application.dto.EquipmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    Equipment toEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO toEquipmentDTO(Equipment equipment);
    List<EquipmentDTO> toEquipmentDTOList(List<Equipment> equipments);
}
