package com.imat.oncomedica.inventory_management.application.usecase.schedule;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import java.util.List;

public class GetAllMaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;


    public GetAllMaintenanceScheduleUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }

    public List<MaintenanceScheduleResponse> execute() {
        return maintenanceScheduleRepository.findAll()
                .stream()
                .map(maintenanceScheduleMapper::buildMaintenanceScheduleResponse)
                .toList();
    }
}
