package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Integer> {
    Optional<MaintenanceSchedule> findMaintenanceScheduleByEquipment_Id(Integer equipmentId);

    @Query("select distinct ms from MaintenanceSchedule ms join ms.monthlyMaintenances mm where mm.year =:year")
    List<MaintenanceSchedule> findByYear(@Param("year") Integer year);
}
