package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceService;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.repository.SpringDataMaintenanceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final SpringDataMaintenanceRepository springDataMaintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceServiceImpl(SpringDataMaintenanceRepository springDataMaintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.springDataMaintenanceRepository = springDataMaintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }


    @Override
    public List<MaintenanceResponse> getAllMaintenances() {
        var maintenances = springDataMaintenanceRepository.findAll();

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
        springDataMaintenanceRepository.deleteById(id);

        return "Maintenance deleted";
    }

}
