package com.imat.oncomedica.inventory_management.application.builder;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMonthlyMaintenanceTypeRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenanceType;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceTypeNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MonthlyMaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MaintenanceScheduleBuilder {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;

    public MaintenanceScheduleBuilder(EquipmentRepository equipmentRepository, MaintenanceStaffRepository maintenanceStaffRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
    }


    public MaintenanceSchedule build(CreateMaintenanceScheduleRequest request) {
        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();

         var equipment = equipmentRepository.findById(request.getEquipmentId())
                 .orElseThrow(() -> new EquipmentNotFoundException(request.getEquipmentId()));

         var staff = maintenanceStaffRepository.findById(request.getMaintenanceStaffId())
                 .orElseThrow(() -> new MaintenanceStaffNotFound(request.getMaintenanceStaffId()));

         maintenanceSchedule.setEquipment(equipment);
         maintenanceSchedule.setResponsible(staff);

         if (request.getMonthlyMaintenances() == null || request.getMonthlyMaintenances().isEmpty()) {
             throw new MonthlyMaintenanceNotFoundException("should have at least one maintenance");
         }

         var monthlyMaintenances = getMonthlyMaintenances(request.getMonthlyMaintenances());

         associateMonthlyMaintenanceToSchedule(monthlyMaintenances, maintenanceSchedule);

         maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenances);

         return maintenanceSchedule;
    }

    private List<MonthlyMaintenance> getMonthlyMaintenances(List<CreateMonthlyMaintenanceRequest> requests) {
        List<MonthlyMaintenance> monthlyMaintenances = new ArrayList<>();

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
                    throw new MaintenanceTypeNotFoundException();
                }

                monthlyMaintenanceTypeEntity.setMonthlyMaintenance(monthlyMaintenance);
                maintenanceTypes.add(monthlyMaintenanceTypeEntity);
            }
            monthlyMaintenance.setMaintenanceTypes(maintenanceTypes);
            monthlyMaintenances.add(monthlyMaintenance);
        }

        return monthlyMaintenances;
    }

    private void associateMonthlyMaintenanceToSchedule(List<MonthlyMaintenance> monthlyMaintenances, MaintenanceSchedule maintenanceSchedule){
        monthlyMaintenances.forEach(mm -> mm.setMaintenanceSchedule(maintenanceSchedule));
    }
}
