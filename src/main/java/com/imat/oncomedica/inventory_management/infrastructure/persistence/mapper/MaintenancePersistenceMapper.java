package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenancePersistenceMapper {

    Maintenance toModel(MaintenanceEntity maintenanceEntity);
    MaintenanceEntity toMaintenanceEntity(Maintenance maintenance);
}
