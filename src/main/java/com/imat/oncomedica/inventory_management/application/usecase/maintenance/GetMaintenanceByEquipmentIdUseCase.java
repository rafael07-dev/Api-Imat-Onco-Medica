package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetMaintenanceByEquipmentIdUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public GetMaintenanceByEquipmentIdUseCase(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public List<MaintenanceResponse> execute(int equipmentId) {
        return maintenanceRepository.findByEquipmentId(equipmentId)
                .stream()
                .map(maintenanceMapper::toMaintenanceResponse)
                .toList();
    }
}
