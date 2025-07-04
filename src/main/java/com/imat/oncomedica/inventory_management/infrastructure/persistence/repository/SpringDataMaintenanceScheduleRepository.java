package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface SpringDataMaintenanceScheduleRepository extends JpaRepository<MaintenanceScheduleEntity, Integer> {
    Optional<MaintenanceScheduleEntity> findMaintenanceScheduleByEquipment_Id(Integer equipmentId);

    @Query("select distinct ms from MaintenanceScheduleEntity ms join ms.monthlyMaintenances mm where mm.year =:year")
    List<MaintenanceScheduleEntity> findByYear(@Param("year") Integer year);
}
