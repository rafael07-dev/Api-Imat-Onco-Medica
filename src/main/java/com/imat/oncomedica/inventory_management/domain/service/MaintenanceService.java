package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import java.util.List;

public interface MaintenanceService {

    List<MaintenanceResponse> getAllMaintenances();
    MaintenanceResponse getMaintenanceById(Integer id);
    List<MaintenanceResponse> getMaintenanceByEquipmentID(Integer equipmentID);
    List<MaintenanceResponse> getEquipmentByStaffId (Integer staffId);
    MaintenanceResponse saveMaintenance(CreateMaintenanceRequest maintenance);
    MaintenanceResponse updateMaintenance(CreateMaintenanceRequest maintenance, Integer id);
    String deleteMaintenance(Integer id);
}
