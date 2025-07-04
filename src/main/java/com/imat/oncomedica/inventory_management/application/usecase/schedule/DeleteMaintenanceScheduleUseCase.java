package com.imat.oncomedica.inventory_management.application.usecase.schedule;

import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;

public class DeleteMaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;

    public DeleteMaintenanceScheduleUseCase(MaintenanceScheduleRepository maintenanceScheduleRepository) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
    }

    public String execute(Integer maintenanceScheduleId) {

        var shedule = maintenanceScheduleRepository.findById(maintenanceScheduleId)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException("Maintenance Schedule Not Found"));

        maintenanceScheduleRepository.delete(shedule);

        return "Maintenance Schedule Deleted";
    }
}
