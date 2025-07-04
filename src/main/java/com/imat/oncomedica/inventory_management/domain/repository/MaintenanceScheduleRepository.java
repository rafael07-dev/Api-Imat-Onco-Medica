package com.imat.oncomedica.inventory_management.domain.repository;

import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleRepository {
    List<MaintenanceSchedule> findAll();
    Optional<MaintenanceSchedule> findById(Integer id);
    MaintenanceSchedule save(MaintenanceSchedule maintenanceSchedule);
    void delete(MaintenanceSchedule maintenanceSchedule);
    List<MaintenanceSchedule> findByYear(Integer year);
    Optional<MaintenanceSchedule> findByEquipmentId(Integer equipmentId);
}
