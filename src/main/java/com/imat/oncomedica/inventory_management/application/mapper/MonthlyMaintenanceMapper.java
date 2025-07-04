package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenanceMapper {

    MonthlyMaintenance toMonthlyMaintenance(CreateMonthlyMaintenanceRequest request);
}
