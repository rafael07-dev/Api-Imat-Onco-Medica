package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceScheduleMapper {

    default List<MaintenanceScheduleResponse> toMaintenanceScheduleList(List<MaintenanceSchedule> maintenanceSchedules, List<MonthlyMaintenance> maintenances){
        List<MaintenanceScheduleResponse> maintenanceScheduleResponses = new ArrayList<>();

        maintenanceSchedules.forEach(m -> {
            var ms = new MaintenanceScheduleResponse(
                    m.getId(),
                    m.getEquipment(),
                    m.getResponsible(),
                    maintenances
            );

            maintenanceScheduleResponses.add(ms);
        });
        return maintenanceScheduleResponses;
    };
    MaintenanceScheduleResponse toMaintenanceScheduleResponse(MaintenanceSchedule maintenanceSchedule);
}
