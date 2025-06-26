package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.CreateMonthlyMaintenanceTypeRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenanceType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenanceTypeMapper {

    MonthlyMaintenanceType toMonthlyMaintenanceType(CreateMonthlyMaintenanceTypeRequest request);
}
