package com.imat.oncomedica.inventory_management.domain.repository;

import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import java.util.List;
import java.util.Optional;

public interface MaintenanceStaffRepository {

    MaintenanceStaff findByName(String name);
    List<MaintenanceStaff> findAll();
    MaintenanceStaff save(MaintenanceStaff maintenanceStaff);
    MaintenanceStaff update(UpdateMaintenanceStaffRequest maintenanceStaff, Integer id);
    Optional<MaintenanceStaff> findById(Integer id);
}
