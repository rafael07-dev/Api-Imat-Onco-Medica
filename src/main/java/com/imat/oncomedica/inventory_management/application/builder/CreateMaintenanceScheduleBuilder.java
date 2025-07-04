package com.imat.oncomedica.inventory_management.application.builder;

import com.imat.oncomedica.inventory_management.application.builder.util.MaintenanceScheduleBuilderHelper;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.factory.MaintenanceScheduleFactory;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.exception.MonthlyMaintenanceNullOrEmptyException;
import org.springframework.stereotype.Component;

@Component
public class CreateMaintenanceScheduleBuilder implements MaintenanceScheduleBuilderStrategy {

    private final MaintenanceScheduleFactory maintenanceScheduleFactory;

    public CreateMaintenanceScheduleBuilder(MaintenanceScheduleFactory maintenanceScheduleFactory) {
        this.maintenanceScheduleFactory = maintenanceScheduleFactory;
    }

    @Override
    public MaintenanceSchedule build(MaintenanceScheduleRequest request) {

        var maintenanceSchedule = maintenanceScheduleFactory.buildMaintenanceSchedule(request);

        if (request.getMonthlyMaintenances() == null || request.getMonthlyMaintenances().isEmpty()) {
            throw new MonthlyMaintenanceNullOrEmptyException("should have at least one maintenance");
        }

        var monthlyMaintenances = MaintenanceScheduleBuilderHelper.getMonthlyMaintenancesFromRequest(request.getMonthlyMaintenances());

        MaintenanceScheduleBuilderHelper.associateMonthlyMaintenanceToSchedule(monthlyMaintenances, maintenanceSchedule);

        maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenances);

        return maintenanceSchedule;
    }

}
