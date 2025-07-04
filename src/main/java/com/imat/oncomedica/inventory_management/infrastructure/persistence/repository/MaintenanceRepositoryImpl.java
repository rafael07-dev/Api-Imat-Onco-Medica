package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.MaintenancePersistenceMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MaintenanceRepositoryImpl implements MaintenanceRepository {

    private final SpringDataMaintenanceRepository managementRepository;
    private final MaintenancePersistenceMapper maintenancePersistenceMapper;

    public MaintenanceRepositoryImpl(SpringDataMaintenanceRepository managementRepository, MaintenancePersistenceMapper maintenancePersistenceMapper) {
        this.managementRepository = managementRepository;
        this.maintenancePersistenceMapper = maintenancePersistenceMapper;
    }

    @Override
    public List<Maintenance> findAll() {
        return managementRepository.findAll()
                .stream()
                .map(maintenancePersistenceMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Maintenance> findById(Integer id) {
        return managementRepository.findById(id)
                .map(maintenancePersistenceMapper::toModel);
    }

    @Override
    public Maintenance save(Maintenance maintenance) {
        var maintenanceEntity = maintenancePersistenceMapper.toMaintenanceEntity(maintenance);
        var maintenanceSaved = managementRepository.save(maintenanceEntity);
        return maintenancePersistenceMapper.toModel(maintenanceSaved);
    }

    @Override
    public Maintenance delete(Maintenance maintenance) {
        var maintenanceEntity = maintenancePersistenceMapper.toMaintenanceEntity(maintenance);
        managementRepository.delete(maintenanceEntity);
        return maintenancePersistenceMapper.toModel(maintenanceEntity);
    }

    @Override
    public List<Maintenance> findByEquipmentId(Integer equipmentId) {
        return managementRepository.findByEquipmentId(equipmentId)
                .stream()
                .map(maintenancePersistenceMapper::toModel)
                .toList();
    }

    @Override
    public List<Maintenance> findByMaintenanceStaffId(Integer maintenanceStaffId) {
        return managementRepository.findByMaintenanceStaffId(maintenanceStaffId)
                .stream()
                .map(maintenancePersistenceMapper::toModel)
                .toList();
    }

    @Override
    public Maintenance findByMaintenanceStaffFirstName(String name) {
        var maintenance = managementRepository.findByMaintenanceStaffFirstName(name);

        return maintenancePersistenceMapper.toModel(maintenance);
    }
}
