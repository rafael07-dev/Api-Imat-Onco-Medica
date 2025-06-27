package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }


    @Override
    public List<MaintenanceResponse> getAllMaintenances() {
        var maintenances = maintenanceRepository.findAll();

        return maintenances.stream()
                .map(maintenanceMapper::toMaintenanceResponse)
                .toList();
    }

    @Override
    public MaintenanceResponse getMaintenanceById(Integer id) {

        return null;
    }

    @Override
    public List<MaintenanceResponse> getMaintenanceByEquipmentID(Integer equipmentID) {
        return null;
    }

    @Override
    public List<MaintenanceResponse> getEquipmentByStaffId(Integer staffId) {
        return List.of();
    }

    @Override
    public MaintenanceResponse saveMaintenance(CreateMaintenanceRequest maintenance) {

        return null;
    }

    @Override
    public MaintenanceResponse updateMaintenance(CreateMaintenanceRequest maintenance, Integer id) {
        return null;
    }

    @Override
    public String deleteMaintenance(Integer id) {
        maintenanceRepository.deleteById(id);

        return "Maintenance deleted";
    }

}
