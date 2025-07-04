package com.imat.oncomedica.inventory_management.application.builder;

import static com.imat.oncomedica.inventory_management.application.builder.util.MaintenanceScheduleBuilderHelper.*;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MonthlyMaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateMaintenanceScheduleBuilder {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;

    public UpdateMaintenanceScheduleBuilder(MaintenanceScheduleRepository maintenanceScheduleRepository) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
    }

    public MaintenanceSchedule build(MaintenanceScheduleRequest request, Integer id) {

        var maintenanceSchedule = maintenanceScheduleRepository.findById(id)
                .orElseThrow(MaintenanceScheduleNotFoundException::new);

        if(!(request instanceof UpdateMaintenanceScheduleRequest)){
            throw new RuntimeException("Invalid request");
        }

        if (request.getMonthlyMaintenances() == null || request.getMonthlyMaintenances().isEmpty()) {
            throw new MonthlyMaintenanceNotFoundException("should have at least one maintenance");
        }

        List<MonthlyMaintenance> monthlyMaintenances = getMonthlyMaintenancesFromRequest(request.getMonthlyMaintenances());

        associateMonthlyMaintenanceToSchedule(monthlyMaintenances, maintenanceSchedule);

        maintenanceSchedule.getMonthlyMaintenances().clear();

        maintenanceSchedule.getMonthlyMaintenances().addAll(monthlyMaintenances);

        return maintenanceSchedule;
    }
}
