package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MonthlyMaintenanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenancePersistenceMapper {

    MonthlyMaintenance toModel(MonthlyMaintenanceEntity monthlyMaintenance);
}
