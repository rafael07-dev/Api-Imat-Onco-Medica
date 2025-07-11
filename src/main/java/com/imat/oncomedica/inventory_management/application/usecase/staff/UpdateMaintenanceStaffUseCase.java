package com.imat.oncomedica.inventory_management.application.usecase.staff;

import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;

public class UpdateMaintenanceStaffUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public UpdateMaintenanceStaffUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    public MaintenanceStaffResponse execute(UpdateMaintenanceStaffRequest request, Integer id) {
        var staffSaved = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        var staffUpdated = maintenanceStaffMapper.updateMaintenanceStaff(request, staffSaved);

        return maintenanceStaffMapper.toMaintenanceStaffResponse(staffUpdated);
    }
}
