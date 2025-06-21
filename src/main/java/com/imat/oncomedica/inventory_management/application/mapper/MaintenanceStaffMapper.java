package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceStaffMapper {

    List<MaintenanceStaffResponse> toMaintenanceStaffResponseList(List<MaintenanceStaff> list);
    MaintenanceStaffResponse toMaintenanceStaffResponse(MaintenanceStaff maintenanceStaff);

    default MaintenanceStaff updateMaintenanceStaff(UpdateMaintenanceStaffRequest request){
        return  new MaintenanceStaff(
                request.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getOccupation(),
                request.isAvailability(),
                request.getMaintenanceCompleted()
        );
    };

    MaintenanceStaff toMaintenanceStaff(CreateMaintenanceStaffRequest request);
}
