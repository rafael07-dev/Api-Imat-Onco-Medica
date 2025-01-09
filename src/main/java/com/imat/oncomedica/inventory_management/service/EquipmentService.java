package com.imat.oncomedica.inventory_management.service;

import com.imat.oncomedica.inventory_management.Models.Equipment;
import com.imat.oncomedica.inventory_management.dto.EquipmentDTO;
import com.imat.oncomedica.inventory_management.mapper.EquipmentMapper;
import com.imat.oncomedica.inventory_management.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    public List<EquipmentDTO> getAllEquipments(){

        List<Equipment> equipmentList = equipmentRepository.findAll();

        return equipmentMapper.toEquipmentDTOList(equipmentList);
    }

    public EquipmentDTO findById(Integer id){
        Equipment equipmentSaved = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        return equipmentMapper.toEquipmentDTO(equipmentSaved);
    }

    public Equipment saveEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    public EquipmentDTO updateEquipment(Integer id, EquipmentDTO equipmentDTO){

        Equipment equipmentSaved = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        equipmentSaved.setEquipmentName(equipmentDTO.getEquipmentName());
        equipmentSaved.setType(equipmentDTO.getType());
        equipmentSaved.setInventoryCode(equipmentDTO.getInventoryCode());
        equipmentSaved.setBrand(equipmentDTO.getBrand());
        equipmentSaved.setModel(equipmentDTO.getModel());
        equipmentSaved.setSeries(equipmentDTO.getSeries());
        equipmentSaved.setLocation(equipmentDTO.getLocation());
        equipmentSaved.setArea(equipmentDTO.getArea());
        equipmentSaved.setFrequency(equipmentDTO.getFrequency());

        equipmentRepository.save(equipmentSaved);

        return equipmentMapper.toEquipmentDTO(equipmentSaved);
    }

    public EquipmentDTO deleteEquipment(Integer id){
        Equipment equipmentSaved = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        equipmentRepository.delete(equipmentSaved);

        return equipmentMapper.toEquipmentDTO(equipmentSaved);
    }
}
