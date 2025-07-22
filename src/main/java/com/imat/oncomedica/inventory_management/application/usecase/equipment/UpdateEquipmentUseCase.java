package com.imat.oncomedica.inventory_management.application.usecase.equipment;

import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateEquipmentUseCase {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public UpdateEquipmentUseCase(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public EquipmentResponse execute(EquipmentRequest request, Integer id) {
        var equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setType(request.getType());
        equipment.setInventoryCode(request.getInventoryCode());
        equipment.setBrand(request.getBrand());
        equipment.setModel(request.getModel());
        equipment.setSeries(request.getSeries());
        equipment.setLocation(request.getLocation());
        equipment.setArea(request.getArea());
        equipment.setFrequency(request.getFrequency());

        return equipmentMapper.toEquipmentResponse(equipmentRepository.save(equipment));
    }
}
