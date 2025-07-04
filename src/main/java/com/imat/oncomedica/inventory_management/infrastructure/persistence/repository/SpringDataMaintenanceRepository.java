package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataMaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> findByEquipmentId(Integer equipmentId);
    List<Maintenance> findByMaintenanceStaffId(Integer maintenanceStaffId);
    Maintenance findByMaintenanceStaffFirstName(String name);
}
