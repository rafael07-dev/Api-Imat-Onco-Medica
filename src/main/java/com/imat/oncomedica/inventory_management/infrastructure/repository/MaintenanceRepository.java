package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> findByEquipmentId(Integer equipmentId);
    List<Maintenance> findByMaintenanceStaffId(Integer maintenanceStaffId);
    Maintenance findByMaintenanceStaffFirstName(String name);
}
