package com.imat.oncomedica.inventory_management.domain.repository;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository {

    List<Maintenance> findAll();
    Optional<Maintenance> findById(Integer id);
    Maintenance save(Maintenance maintenance);
    Maintenance delete(Maintenance maintenance);
    List<Maintenance> findByEquipmentId(Integer equipmentId);
    List<Maintenance> findByMaintenanceStaffId(Integer maintenanceStaffId);
    Maintenance findByMaintenanceStaffFirstName(String name);
}
