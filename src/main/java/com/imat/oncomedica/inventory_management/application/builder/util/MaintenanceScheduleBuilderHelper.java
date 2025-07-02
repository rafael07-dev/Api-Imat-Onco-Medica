package com.imat.oncomedica.inventory_management.application.builder.util;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMonthlyMaintenanceTypeRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenanceType;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceTypeNotAllowedException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceTypeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceScheduleBuilderHelper {

    public static List<MonthlyMaintenance> getMonthlyMaintenancesFromRequest(List<CreateMonthlyMaintenanceRequest> requests) {
        List<MonthlyMaintenance> monthlyMaintenances = new ArrayList<>();

        if (requests == null || requests.isEmpty()) {
            throw new MaintenanceTypeNotFoundException("Requests is empty");
        }

        for (CreateMonthlyMaintenanceRequest request : requests) {

            MonthlyMaintenance monthlyMaintenance = new MonthlyMaintenance();
            monthlyMaintenance.setMonth(request.getMonth());
            monthlyMaintenance.setYear(request.getYear());

            List<MonthlyMaintenanceType> maintenanceTypes = new ArrayList<>();

            for (CreateMonthlyMaintenanceTypeRequest monthlyMaintenanceType: request.getMaintenanceType()){
                var monthlyMaintenanceTypeEntity = new MonthlyMaintenanceType();

                monthlyMaintenanceTypeEntity.setQuantity(monthlyMaintenanceType.getQuantity());

                var maintenanceType = monthlyMaintenanceType.getMaintenanceType().toUpperCase();

                try{
                    var maintenanceTypeEnum = MaintenanceTypeEnum.valueOf(maintenanceType);
                    monthlyMaintenanceTypeEntity.setMaintenanceTypeEnum(maintenanceTypeEnum);
                }catch (IllegalArgumentException e){
                    throw new MaintenanceTypeNotAllowedException();
                }

                monthlyMaintenanceTypeEntity.setMonthlyMaintenance(monthlyMaintenance);
                maintenanceTypes.add(monthlyMaintenanceTypeEntity);
            }
            monthlyMaintenance.setMaintenanceTypes(maintenanceTypes);
            monthlyMaintenances.add(monthlyMaintenance);
        }

        return monthlyMaintenances;
    }

    public static void associateMonthlyMaintenanceToSchedule(List<MonthlyMaintenance> monthlyMaintenances, MaintenanceSchedule maintenanceSchedule){
        monthlyMaintenances.forEach(mm -> mm.setMaintenanceSchedule(maintenanceSchedule));
    }
}
