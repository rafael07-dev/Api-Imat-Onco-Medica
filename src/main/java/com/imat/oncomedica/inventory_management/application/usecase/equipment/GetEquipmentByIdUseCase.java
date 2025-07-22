package com.imat.oncomedica.inventory_management.application.usecase.equipment;

import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import org.springframework.stereotype.Component;

@Component
public class GetEquipmentByIdUseCase {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public GetEquipmentByIdUseCase(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public EquipmentResponse execute(Integer equipmentId) {
        var equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException(equipmentId));
        return equipmentMapper.toEquipmentResponse(equipment);
    }
}
