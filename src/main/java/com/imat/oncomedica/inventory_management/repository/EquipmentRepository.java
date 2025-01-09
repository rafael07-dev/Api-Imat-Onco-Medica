package com.imat.oncomedica.inventory_management.repository;

import com.imat.oncomedica.inventory_management.Models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
