package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.equipment.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;

public class CreateEquipmentUseCase {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;


    public CreateEquipmentUseCase(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public EquipmentResponse execute(CreateEquipmentRequest request) {
        Equipment equipment = equipmentMapper.toEquipment(request);
        Equipment saved = equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(saved);
    }
}
