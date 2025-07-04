package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MonthlyMaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MonthlyMaintenanceTypeResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceScheduleMapper {

    default MaintenanceScheduleResponse buildMaintenanceScheduleResponse(MaintenanceSchedule maintenanceSchedule){
        MaintenanceScheduleResponse maintenanceScheduleResponse = new MaintenanceScheduleResponse();

        maintenanceScheduleResponse.setId(maintenanceSchedule.getId());
        maintenanceScheduleResponse.setEquipment(maintenanceSchedule.getEquipment());
        maintenanceScheduleResponse.setResponsible(maintenanceSchedule.getResponsible());

        List<MonthlyMaintenanceResponse> monthlyMaintenanceResponseList = new ArrayList<>();

         maintenanceSchedule.getMonthlyMaintenances()
                .forEach(mm -> {
                    var maintenanceResponse = new MonthlyMaintenanceResponse();

                    maintenanceResponse.setMonth(mm.getMonth());
                    maintenanceResponse.setYear(mm.getYear());

                    List<MonthlyMaintenanceTypeResponse> monthlyMaintenanceTypeResponses = new ArrayList<>();

                    mm.getMaintenanceTypes()
                            .forEach(mmt -> {
                                var maintenanceTypeResponse = new MonthlyMaintenanceTypeResponse();
                                maintenanceTypeResponse.setQuantity(mmt.getQuantity());

                                var maintenanceType = mmt.getMaintenanceTypeEnum().toString();
                                maintenanceTypeResponse.setMaintenanceType(maintenanceType);

                                monthlyMaintenanceTypeResponses.add(maintenanceTypeResponse);
                            });
                    maintenanceResponse.setMaintenances(monthlyMaintenanceTypeResponses);
                    monthlyMaintenanceResponseList.add(maintenanceResponse);
                });

        maintenanceScheduleResponse.setMonthlyMaintenances(monthlyMaintenanceResponseList);

        return maintenanceScheduleResponse;
    };
}
