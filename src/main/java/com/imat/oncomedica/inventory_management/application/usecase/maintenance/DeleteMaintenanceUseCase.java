package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;

    public DeleteMaintenanceUseCase(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public String execute(Integer maintenanceId) {

        var maintenanceSaved = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(maintenanceId));

        maintenanceRepository.delete(maintenanceSaved);

        return "Maintenance deleted";
    }
}
