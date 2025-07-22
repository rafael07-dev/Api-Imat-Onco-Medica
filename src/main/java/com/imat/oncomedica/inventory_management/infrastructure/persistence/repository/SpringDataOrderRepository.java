package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByMaintenanceStaff_Id(Integer id);
    Optional<OrderEntity> findByMaintenance_Id(Integer id);
}
