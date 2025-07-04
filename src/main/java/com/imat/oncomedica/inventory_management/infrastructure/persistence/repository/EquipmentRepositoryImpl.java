package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.EquipmentEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final SpringDataEquipmentRepository equipmentRepository;

    public EquipmentRepositoryImpl(SpringDataEquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Optional<Equipment> findById(Integer id) {
        return equipmentRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Equipment> findAll() {
        List<EquipmentEntity> equipmentList = equipmentRepository.findAll();

        return equipmentList
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Equipment save(Equipment equipment) {
        EquipmentEntity equipmentToSave = toEntity(equipment);
        return toDomain(equipmentRepository.save(equipmentToSave));
    }

    @Override
    public Equipment delete(Integer id) {
        EquipmentEntity equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        equipmentRepository.delete(equipment);
        return toDomain(equipment);
    }

    private EquipmentEntity toEntity(Equipment equipment) {
        EquipmentEntity entity = new EquipmentEntity();
        entity.setId(equipment.getId());
        entity.setEquipmentName(equipment.getEquipmentName());
        entity.setImageUrl(equipment.getImageUrl());
        entity.setType(equipment.getType());
        entity.setInventoryCode(equipment.getInventoryCode());
        entity.setBrand(equipment.getBrand());
        entity.setModel(equipment.getModel());
        entity.setSeries(equipment.getSeries());
        entity.setLocation(equipment.getLocation());
        entity.setArea(equipment.getArea());
        entity.setFrequency(equipment.getFrequency());
        entity.setFloor(equipment.getFloor());
        entity.setTower(equipment.getTower());
        return entity;
    }

    private Equipment toDomain(EquipmentEntity equipmentEntity) {
        Equipment equipment = new Equipment();
        equipment.setId(equipmentEntity.getId());
        equipment.setEquipmentName(equipmentEntity.getEquipmentName());
        equipment.setImageUrl(equipmentEntity.getImageUrl());
        equipment.setType(equipmentEntity.getType());
        equipment.setInventoryCode(equipmentEntity.getInventoryCode());
        equipment.setBrand(equipmentEntity.getBrand());
        equipment.setModel(equipmentEntity.getModel());
        equipment.setSeries(equipmentEntity.getSeries());
        equipment.setLocation(equipmentEntity.getLocation());
        equipment.setArea(equipmentEntity.getArea());
        equipment.setFrequency(equipmentEntity.getFrequency());
        equipment.setFloor(equipmentEntity.getFloor());
        equipment.setTower(equipmentEntity.getTower());
        return equipment;
    }
}
