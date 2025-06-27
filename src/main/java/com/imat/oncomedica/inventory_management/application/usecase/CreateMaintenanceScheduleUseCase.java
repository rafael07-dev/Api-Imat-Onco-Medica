package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.builder.MaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleAlreadyExistsException;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateMaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    private final MaintenanceScheduleBuilder maintenanceScheduleBuilder;

    public CreateMaintenanceScheduleUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository,
                                            MaintenanceScheduleMapper maintenanceScheduleMapper, MaintenanceScheduleBuilder maintenanceScheduleBuilder) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.maintenanceScheduleBuilder = maintenanceScheduleBuilder;
    }

    public MaintenanceScheduleResponse execute(CreateMaintenanceScheduleRequest request) {

        if (maintenanceScheduleRepository.findMaintenanceScheduleByEquipmentId(request.getEquipmentId())){
            throw new MaintenanceScheduleAlreadyExistsException(request.getEquipmentId());
        }

        var maintenanceSchedule =  maintenanceScheduleBuilder.build(request);

        var maintenanceSaved = maintenanceScheduleRepository.save(maintenanceSchedule);

        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceSaved);
    }
}
