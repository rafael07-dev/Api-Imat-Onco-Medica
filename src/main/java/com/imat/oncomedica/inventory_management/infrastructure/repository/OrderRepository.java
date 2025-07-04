package com.imat.oncomedica.inventory_management.infrastructure.repository;

import com.imat.oncomedica.inventory_management.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByMaintenanceStaff_Id(Integer id);
    Order findByMaintenance_Id(Integer id);
}
