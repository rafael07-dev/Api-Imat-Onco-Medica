package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenanceMapper {

    MonthlyMaintenance toMonthlyMaintenance(CreateMonthlyMaintenanceRequest request);
}
