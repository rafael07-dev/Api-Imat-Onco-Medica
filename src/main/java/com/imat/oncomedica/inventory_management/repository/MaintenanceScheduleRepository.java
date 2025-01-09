package com.imat.oncomedica.inventory_management.repository;

import com.imat.oncomedica.inventory_management.Models.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Integer> {
}
