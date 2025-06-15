package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Integer> {
}
