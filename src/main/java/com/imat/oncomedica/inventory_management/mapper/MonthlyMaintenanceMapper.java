package com.imat.oncomedica.inventory_management.mapper;

import com.imat.oncomedica.inventory_management.Models.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.dto.MonthlyMaintenanceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthlyMaintenanceMapper {

    List<MonthlyMaintenance> toMonthlyMaintenance(List<MonthlyMaintenanceDTO> monthlyMaintenanceDTO);
}
