package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;

public class DeleteEquipmentUseCase {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public DeleteEquipmentUseCase(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public EquipmentResponse execute(Integer equipmentId) {
        var equipmentDeleted = equipmentRepository.delete(equipmentId);
        return equipmentMapper.toEquipmentResponse(equipmentDeleted);
    }
}
