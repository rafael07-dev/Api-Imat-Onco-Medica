package com.imat.oncomedica.inventory_management.application.mapper;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.Maintenance;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceDTO;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    default MaintenanceResponse toMaintenanceResponse(Maintenance maintenance){

        return new MaintenanceResponse(
                maintenance.getId(),
                maintenance.getEquipment().getInventoryCode(),
                maintenance.getMaintenanceStaff().getFirstName() + maintenance.getMaintenanceStaff().getLastName(),
                maintenance.getMaintenanceStaff().getMaintenanceCompleted(),
                maintenance.getMaintenanceStaff().getOccupation(),
                maintenance.getScheduledDate(),
                maintenance.getStartDate(),
                maintenance.getStartTime(),
                maintenance.getDeliveryDate(),
                maintenance.getTimeUsed(),
                maintenance.getRegistrationDate(),
                maintenance.getDateOfCompletion(),
                maintenance.getObservations()
        );
    };

    default Maintenance toMaintenance(CreateMaintenanceRequest request, Equipment equipment, MaintenanceStaff maintenanceStaff){

        return new Maintenance(
                null,
                equipment,
                maintenanceStaff,
                request.getScheduledDate(),
                request.getStartDate(),
                null,
                request.getDeliveryDate(),
                null,
                request.getRegistrationDate(),
                null,
                request.getTypeMaintenance(),
                request.getObservations()
        );
    };

    List<MaintenanceDTO> toMaintenanceResponseList(List<Maintenance> maintenances);
}
