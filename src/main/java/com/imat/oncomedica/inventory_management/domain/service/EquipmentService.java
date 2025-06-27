package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.equipment.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.dto.equipment.UpdateEquipmentRequest;

import java.util.List;

public interface EquipmentService {

    EquipmentResponse getEquipmentById(Integer id);
    List<EquipmentResponse> getAllEquipments();
    EquipmentResponse saveEquipment(CreateEquipmentRequest request);
    EquipmentResponse updateEquipment(UpdateEquipmentRequest request, Integer id);
    EquipmentResponse deleteEquipment(Integer id);
}
