package com.imat.oncomedica.inventory_management.application.usecase.staff;

import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;

public class GetMaintenanceStaffByIdUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public GetMaintenanceStaffByIdUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    public MaintenanceStaffResponse execute(Integer id) {
        return maintenanceStaffRepository.findById(id)
                .map(maintenanceStaffMapper::toMaintenanceStaffResponse)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));
    }
}
