package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MonthlyMaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataMonthlyMaintenanceRepository extends JpaRepository<MonthlyMaintenanceEntity, Integer> {
    List<MonthlyMaintenance> findByMaintenanceSchedule_Id(Integer id);
}
