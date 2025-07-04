package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
}
