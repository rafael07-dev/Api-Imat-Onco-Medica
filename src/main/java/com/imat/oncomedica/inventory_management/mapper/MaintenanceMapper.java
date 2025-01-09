package com.imat.oncomedica.inventory_management.mapper;

import com.imat.oncomedica.inventory_management.Models.Maintenance;
import com.imat.oncomedica.inventory_management.dto.MaintenanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    MaintenanceDTO toMaintenanceDTO(Maintenance maintenance);
    Maintenance toMaintenance(MaintenanceDTO maintenanceDTO);
}
