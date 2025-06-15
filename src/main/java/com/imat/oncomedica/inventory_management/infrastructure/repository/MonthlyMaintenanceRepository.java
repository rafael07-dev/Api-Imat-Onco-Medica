package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyMaintenanceRepository extends JpaRepository<MonthlyMaintenance, Integer> {
}
