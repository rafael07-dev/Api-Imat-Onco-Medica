package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import java.util.List;

public interface MaintenanceStaffService {

    MaintenanceStaffResponse getMaintenanceStaffByName(String name);
    List<MaintenanceStaffResponse> getAllMaintenanceStaff();
    MaintenanceStaffResponse createMaintenanceStaff(CreateMaintenanceStaffRequest maintenanceStaff);
    MaintenanceStaffResponse updateMaintenanceStaff(UpdateMaintenanceStaffRequest maintenanceStaff, Integer id);
    MaintenanceStaffResponse getMaintenanceStaffById(Integer id);
}
