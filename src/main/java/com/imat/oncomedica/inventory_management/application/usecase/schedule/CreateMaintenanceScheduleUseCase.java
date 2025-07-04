package com.imat.oncomedica.inventory_management.application.usecase.schedule;

import com.imat.oncomedica.inventory_management.application.builder.CreateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleAlreadyExistsException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;

public class CreateMaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    private final CreateMaintenanceScheduleBuilder createMaintenanceScheduleBuilder;

    public CreateMaintenanceScheduleUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository,
                                            MaintenanceScheduleMapper maintenanceScheduleMapper,
                                            CreateMaintenanceScheduleBuilder createMaintenanceScheduleBuilder) {

        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.createMaintenanceScheduleBuilder = createMaintenanceScheduleBuilder;
    }

    public MaintenanceScheduleResponse execute(CreateMaintenanceScheduleRequest request) {

        if (maintenanceScheduleRepository.findByEquipmentId(request.getEquipmentId()).isEmpty()){
            throw new MaintenanceScheduleAlreadyExistsException(request.getEquipmentId());
        }

        var maintenanceSchedule =  createMaintenanceScheduleBuilder.build(request);

        var maintenanceSaved = maintenanceScheduleRepository.save(maintenanceSchedule);

        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceSaved);
    }
}
