package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateEquipmentRequest;
import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.application.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.EquipmentService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public EquipmentResponse getEquipmentById(Integer id) {
        Equipment e = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        return equipmentMapper.toEquipmentResponse(e);
    }

    @Override
    public List<EquipmentResponse> getAllEquipments(){

        List<Equipment> equipmentList = equipmentRepository.findAll();

        return equipmentMapper.toEquipmentResponseList(equipmentList);
    }

    @Override
    public EquipmentResponse saveEquipment(CreateEquipmentRequest request) {
        Equipment equipmentToSave = equipmentMapper.toEquipment(request);

        return equipmentMapper.toEquipmentResponse(equipmentRepository.save(equipmentToSave));
    }

    @Override
    public EquipmentResponse updateEquipment(UpdateEquipmentRequest request, Integer id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        equipment.setId(id);
        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setType(request.getType());
        equipment.setInventoryCode(request.getInventoryCode());
        equipment.setBrand(request.getBrand());
        equipment.setModel(request.getModel());
        equipment.setSeries(request.getSeries());
        equipment.setLocation(request.getLocation());
        equipment.setArea(request.getArea());
        equipment.setFrequency(request.getFrequency());

        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Override
    public EquipmentResponse deleteEquipment(Integer id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        equipmentRepository.delete(equipment);
        return equipmentMapper.toEquipmentResponse(equipment);
    }

}
