package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import java.util.List;

public interface MaintenanceScheduleService {
    List<MaintenanceScheduleResponse> getAllMaintenanceSchedules();
    MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id);
    List<MaintenanceScheduleResponse> getMaintenanceScheduleByYear(Integer year);
    String deleteMaintenanceSchedule(Integer id);
}
