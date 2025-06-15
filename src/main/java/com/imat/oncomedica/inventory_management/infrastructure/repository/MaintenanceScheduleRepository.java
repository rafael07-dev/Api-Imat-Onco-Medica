package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Integer> {
}
