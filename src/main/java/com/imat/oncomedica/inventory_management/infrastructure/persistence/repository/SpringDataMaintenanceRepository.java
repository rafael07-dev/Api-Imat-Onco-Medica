package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataMaintenanceRepository extends JpaRepository<MaintenanceEntity, Integer> {

    List<MaintenanceEntity> findByEquipmentId(Integer equipmentId);
    List<MaintenanceEntity> findByMaintenanceStaffId(Integer maintenanceStaffId);
    MaintenanceEntity findByMaintenanceStaffFirstName(String name);
}
