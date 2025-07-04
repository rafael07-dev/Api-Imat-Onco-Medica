package com.imat.oncomedica.inventory_management.domain.repository;

import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import java.util.List;
import java.util.Optional;

public interface EquipmentRepository {

    Optional<Equipment> findById(Integer id);
    List<Equipment> findAll();
    Equipment save(Equipment equipment);
    Equipment delete(Integer id);
}
