package com.imat.oncomedica.inventory_management.repository;

import com.imat.oncomedica.inventory_management.Models.MonthlyMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyMaintenanceRepository extends JpaRepository<MonthlyMaintenance, Integer> {
}
