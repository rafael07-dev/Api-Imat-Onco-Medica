package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.application.dto.MonthlyMaintenanceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenanceMapper {

    List<MonthlyMaintenance> toMonthlyMaintenance(List<MonthlyMaintenanceDTO> monthlyMaintenanceDTO);
}
