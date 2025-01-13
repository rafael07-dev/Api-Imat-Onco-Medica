package com.imat.oncomedica.inventory_management.repository;

import com.imat.oncomedica.inventory_management.Models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> findByEquipmentId(Integer equipmentId);
}
