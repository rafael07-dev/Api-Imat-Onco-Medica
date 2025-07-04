package com.imat.oncomedica.inventory_management.application.builder;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;

public interface MaintenanceScheduleBuilderStrategy {

    MaintenanceSchedule build(MaintenanceScheduleRequest request);
}
