package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceScheduleRequest;

import java.util.List;

public interface MaintenanceScheduleService {
    List<MaintenanceScheduleResponse> getAllMaintenanceSchedules();
    MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id);
    MaintenanceScheduleResponse updateMaintenanceSchedule(UpdateMaintenanceScheduleRequest maintenanceSchedule, Integer id);
    MaintenanceScheduleResponse createMaintenanceSchedule(CreateMaintenanceScheduleRequest createMaintenanceSchedule);
    String deleteMaintenanceSchedule(Integer id);
}
