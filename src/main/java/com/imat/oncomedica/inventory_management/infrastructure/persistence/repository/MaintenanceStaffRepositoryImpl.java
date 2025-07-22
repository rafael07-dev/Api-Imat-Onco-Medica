package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceStaffEntity;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.MaintenanceStaffPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MaintenanceStaffRepositoryImpl implements MaintenanceStaffRepository {

    private final SpringDataMaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffPersistenceMapper maintenanceStaffMapper;

    public MaintenanceStaffRepositoryImpl(SpringDataMaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffPersistenceMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    @Override
    public MaintenanceStaff findByName(String name) {
        return maintenanceStaffRepository.findByFirstName(name)
                .map(maintenanceStaffMapper::toModel)
                .orElseThrow(() -> new MaintenanceStaffNotFound("Maintenance staff not found"));
    }

    @Override
    public List<MaintenanceStaff> findAll() {
        return maintenanceStaffRepository.findAll()
                .stream()
                .map(maintenanceStaffMapper::toModel)
                .toList();
    }

    @Override
    public MaintenanceStaff save(MaintenanceStaff maintenanceStaff) {
        MaintenanceStaffEntity ms = maintenanceStaffMapper.toEntity(maintenanceStaff);

        if (ms == null){
            throw new MaintenanceStaffNotFound();
        }

        maintenanceStaffRepository.save(ms);

        return maintenanceStaffMapper.toModel(ms);
    }

    @Override
    public MaintenanceStaff update(UpdateMaintenanceStaffRequest request, Integer id) {
        var maintenanceStaffSaved = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        var maintenanceStaffUpdated = maintenanceStaffMapper.updateMaintenanceStaffFromRequest(request, maintenanceStaffSaved);
        var staffSaved = maintenanceStaffRepository.save(maintenanceStaffUpdated);
        return maintenanceStaffMapper.toModel(staffSaved);
    }

    @Override
    public Optional<MaintenanceStaff> findById(Integer id) {
        return maintenanceStaffRepository.findById(id)
                .map(maintenanceStaffMapper::toModel);
    }
}
