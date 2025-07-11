package com.imat.oncomedica.inventory_management.application.usecase.staff;

import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import java.util.List;

public class GetAllMaintenanceStaffUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public GetAllMaintenanceStaffUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    public List<MaintenanceStaffResponse> execute() {

        return maintenanceStaffRepository.findAll()
                .stream()
                .map(maintenanceStaffMapper::toMaintenanceStaffResponse)
                .toList();
    }
}
