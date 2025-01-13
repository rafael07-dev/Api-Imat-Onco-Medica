package com.imat.oncomedica.inventory_management.mapper;

import com.imat.oncomedica.inventory_management.Models.Maintenance;
import com.imat.oncomedica.inventory_management.dto.MaintenanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(source = "maintenance.equipment.id", target = "equipmentId")
    @Mapping(source = "maintenance.maintenanceStaff.id", target = "maintenanceStaffId")
    MaintenanceDTO toMaintenanceDTO(Maintenance maintenance);

    //@Mapping(source = "maintenanceDTO.equipmentId", target = "equipment")
    Maintenance toMaintenance(MaintenanceDTO maintenanceDTO);

    List<MaintenanceDTO> toMaintenanceDtoList(List<Maintenance> maintenances);
}
