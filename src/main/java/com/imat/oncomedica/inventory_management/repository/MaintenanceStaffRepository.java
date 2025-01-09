package com.imat.oncomedica.inventory_management.repository;

import com.imat.oncomedica.inventory_management.Models.MaintenanceStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceStaffRepository extends JpaRepository<MaintenanceStaff, Integer> {
}
