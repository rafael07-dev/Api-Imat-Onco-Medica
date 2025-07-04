package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceSchedulePersistenceMapper {

    MaintenanceSchedule toModel(MaintenanceScheduleEntity maintenanceSchedule);
    MaintenanceScheduleEntity toEntity(MaintenanceSchedule maintenanceSchedule);
}
