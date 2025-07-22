package com.imat.oncomedica.inventory_management.infrastructure.persistence.builder;

import com.imat.oncomedica.inventory_management.domain.model.*;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.*;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.EquipmentPersistenceMapper;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.MaintenanceStaffPersistenceMapper;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.MonthlyMaintenancePersistenceMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MaintenanceSchedulePersistenceBuilder {

    private final EquipmentPersistenceMapper equipmentPersistenceMapper;
    private final MaintenanceStaffPersistenceMapper maintenanceStaffPersistenceMapper;
    private final MonthlyMaintenancePersistenceMapper monthlyMaintenancePersistenceMapper;

    public MaintenanceSchedulePersistenceBuilder(EquipmentPersistenceMapper equipmentPersistenceMapper, MaintenanceStaffPersistenceMapper maintenanceStaffPersistenceMapper, MonthlyMaintenancePersistenceMapper monthlyMaintenancePersistenceMapper) {
        this.equipmentPersistenceMapper = equipmentPersistenceMapper;
        this.maintenanceStaffPersistenceMapper = maintenanceStaffPersistenceMapper;
        this.monthlyMaintenancePersistenceMapper = monthlyMaintenancePersistenceMapper;
    }


    public MaintenanceSchedule build(MaintenanceScheduleEntity entity){
        var maintenanceSchedule = new MaintenanceSchedule();

        maintenanceSchedule.setId(entity.getId());

        var equipmentModel =  setEquipment(entity.getEquipment());
        var staffModel = setMaintenanceStaff(entity.getResponsible());
        var monthlyMaintenanceModel = toMonthlyMaintenanceModel(entity.getMonthlyMaintenances());

        maintenanceSchedule.setEquipment(equipmentModel);
        maintenanceSchedule.setResponsible(staffModel);
        maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenanceModel);

        return maintenanceSchedule;
    }

    private Equipment setEquipment(EquipmentEntity equipment){
        return equipmentPersistenceMapper.toEquipmentModel(equipment);
    }

    private MaintenanceStaff setMaintenanceStaff(MaintenanceStaffEntity staffEntity){
        return maintenanceStaffPersistenceMapper.toModel(staffEntity);
    }

    private List<MonthlyMaintenance> toMonthlyMaintenanceModel(List<MonthlyMaintenanceEntity> maintenanceEntityList){
        return maintenanceEntityList
                .stream()
                .map(monthlyMaintenancePersistenceMapper::toModel)
                .toList();
    }
}
