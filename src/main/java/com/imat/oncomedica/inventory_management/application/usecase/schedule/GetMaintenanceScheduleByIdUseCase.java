package com.imat.oncomedica.inventory_management.application.usecase.schedule;


import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;

public class GetMaintenanceScheduleByIdUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;


    public GetMaintenanceScheduleByIdUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }

    public MaintenanceScheduleResponse execute(Integer id) {
        return maintenanceScheduleRepository.findById(id)
                .map(maintenanceScheduleMapper::buildMaintenanceScheduleResponse)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException("Maintenance Schedule not found"));
    }
}
