package com.imat.oncomedica.inventory_management.application.usecase.schedule;

import com.imat.oncomedica.inventory_management.application.builder.UpdateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;

public class UpdateMaintenanceScheduleUseCase {

    private final UpdateMaintenanceScheduleBuilder updateMaintenanceScheduleBuilder;
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;

    public UpdateMaintenanceScheduleUseCase(UpdateMaintenanceScheduleBuilder updateMaintenanceScheduleBuilder, MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.updateMaintenanceScheduleBuilder = updateMaintenanceScheduleBuilder;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }


    public MaintenanceScheduleResponse execute(UpdateMaintenanceScheduleRequest request, Integer id) {

        var maintenanceSchedule = updateMaintenanceScheduleBuilder.build(request, id);

        var maintenanceScheduleSaved = maintenanceScheduleRepository.save(maintenanceSchedule);
        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceScheduleSaved);
    }
}
