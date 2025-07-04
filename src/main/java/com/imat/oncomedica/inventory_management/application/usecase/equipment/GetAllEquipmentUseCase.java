package com.imat.oncomedica.inventory_management.application.usecase.equipment;

import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import java.util.List;

public class GetAllEquipmentUseCase {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public GetAllEquipmentUseCase(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public List<EquipmentResponse> execute() {
        return equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toEquipmentResponse)
                .toList();
    }
}
