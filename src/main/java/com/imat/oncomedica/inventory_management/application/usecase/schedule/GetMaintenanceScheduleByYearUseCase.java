package com.imat.oncomedica.inventory_management.application.usecase.schedule;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import java.util.List;

public class GetMaintenanceScheduleByYearUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;

    public GetMaintenanceScheduleByYearUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }

    public List<MaintenanceScheduleResponse> execute(Integer year) {

        var maintenances = maintenanceScheduleRepository.findByYear(year);

        if (maintenances == null || maintenances.isEmpty())
            throw new MaintenanceScheduleNotFoundException("there is no maintenances for this year");

        return maintenances
                .stream()
                .map(maintenanceScheduleMapper::buildMaintenanceScheduleResponse)
                .toList();
    }
}
