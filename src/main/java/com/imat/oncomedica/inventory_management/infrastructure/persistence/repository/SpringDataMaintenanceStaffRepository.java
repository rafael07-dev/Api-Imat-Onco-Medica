package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpringDataMaintenanceStaffRepository extends JpaRepository<MaintenanceStaffEntity, Integer> {

    Optional<MaintenanceStaffEntity> findByFirstName(String name);
}
