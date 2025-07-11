package com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.entity.MaintenanceStaffEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaintenanceStaffPersistenceMapper {

    MaintenanceStaff toModel(MaintenanceStaffEntity maintenanceStaff);
    MaintenanceStaffEntity toEntity(MaintenanceStaff maintenanceStaff);

    default MaintenanceStaffEntity toEntity(CreateMaintenanceStaffRequest request){
        return  new MaintenanceStaffEntity(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getOccupation(),
                request.isAvailability(),
                request.getMaintenancesCompleted(),
                null,
                null
        );
    };

    default MaintenanceStaffEntity updateMaintenanceStaffFromRequest(UpdateMaintenanceStaffRequest request, MaintenanceStaffEntity staff){
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setOccupation(request.getOccupation());
        staff.setEmail(request.getEmail());
        staff.setAvailability(request.isAvailability());
        request.setMaintenanceCompleted(request.getMaintenanceCompleted());
        return  staff;
    };
}
