package com.imat.oncomedica.inventory_management.application.factory;

import com.imat.oncomedica.inventory_management.application.builder.util.MaintenanceScheduleBuilderHelper;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceScheduleFactory {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;

    public MaintenanceScheduleFactory(EquipmentRepository equipmentRepository, MaintenanceStaffRepository maintenanceStaffRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
    }

    public MaintenanceSchedule buildMaintenanceSchedule(MaintenanceScheduleRequest request) {
        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();

        var equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(request.getEquipmentId()));

        var staff = maintenanceStaffRepository.findById(request.getMaintenanceStaffId())
                .orElseThrow(() -> new MaintenanceStaffNotFound(request.getMaintenanceStaffId()));

        maintenanceSchedule.setEquipment(equipment);
        maintenanceSchedule.setResponsible(staff);

        var monthlyMaintenances = MaintenanceScheduleBuilderHelper.getMonthlyMaintenancesFromRequest(request.getMonthlyMaintenances());

        maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenances);

        return maintenanceSchedule;
    }
}
