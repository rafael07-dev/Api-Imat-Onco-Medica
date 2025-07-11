package com.imat.oncomedica.inventory_management.application.usecase.staff;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;

public class CreateMaintenanceStaffUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public CreateMaintenanceStaffUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    public MaintenanceStaffResponse execute(CreateMaintenanceStaffRequest request) {
        var newStaff = maintenanceStaffMapper.toMaintenanceStaff(request);

        maintenanceStaffRepository.save(newStaff);
        return maintenanceStaffMapper.toMaintenanceStaffResponse(newStaff);
    }
}
