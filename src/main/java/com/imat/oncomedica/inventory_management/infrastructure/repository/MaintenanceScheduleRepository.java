package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Integer> {
    Optional<MaintenanceSchedule> findMaintenanceScheduleByEquipment_Id(Integer equipmentId);
}
