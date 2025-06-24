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

    default MaintenanceStaff updateMaintenanceStaff(UpdateMaintenanceStaffRequest request, MaintenanceStaff staff){
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setOccupation(request.getOccupation());
        staff.setEmail(request.getEmail());
        staff.setAvailability(request.isAvailability());
        request.setMaintenanceCompleted(request.getMaintenanceCompleted());
        return  staff;
    };

    default MaintenanceStaff toMaintenanceStaff(CreateMaintenanceStaffRequest request){
        return  new MaintenanceStaff(
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
}
