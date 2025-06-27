package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import java.util.List;

public interface MaintenanceScheduleService {
    List<MaintenanceScheduleResponse> getAllMaintenanceSchedules();
    MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id);
    MaintenanceScheduleResponse updateMaintenanceSchedule(UpdateMaintenanceScheduleRequest maintenanceSchedule, Integer id);
    String deleteMaintenanceSchedule(Integer id);
}
