package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetMaintenancesByMaintenanceStaffIdUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public GetMaintenancesByMaintenanceStaffIdUseCase(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public List<MaintenanceResponse> execute(int staffId) {
        return maintenanceRepository.findByMaintenanceStaffId(staffId)
                .stream()
                .map(maintenanceMapper::toMaintenanceResponse)
                .toList();
    }
}
