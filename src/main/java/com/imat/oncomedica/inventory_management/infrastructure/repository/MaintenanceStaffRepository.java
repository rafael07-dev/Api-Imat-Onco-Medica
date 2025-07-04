package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Integer> {

    MaintenanceStaff findByFirstName(String name);
}
