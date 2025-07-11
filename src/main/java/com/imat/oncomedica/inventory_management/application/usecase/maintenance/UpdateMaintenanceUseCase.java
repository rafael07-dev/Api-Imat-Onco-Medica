package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.model.Maintenance;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;

public class UpdateMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final EquipmentRepository equipmentRepository;

    public UpdateMaintenanceUseCase(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, MaintenanceStaffRepository maintenanceStaffRepository, EquipmentRepository equipmentRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public MaintenanceResponse execute(Integer maintenanceId, UpdateMaintenanceRequest request){

        var maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(maintenanceId));

        var staff = maintenance.getMaintenanceStaff();

        var equipment = equipmentRepository.findById(maintenance.getEquipment().getId())
                .orElseThrow(() -> new EquipmentNotFoundException(maintenance.getEquipment().getId()));

        updateMaintenance(maintenance, request, staff, equipment);

        staff.setMaintenanceCompleted(staff.getMaintenanceCompleted() + 1);
        maintenanceStaffRepository.save(staff);

        var maintenanceSaved = maintenanceRepository.save(maintenance);

        return maintenanceMapper.toMaintenanceResponse(maintenanceSaved);
    }

    private void updateMaintenance(Maintenance maintenance,
                                   UpdateMaintenanceRequest request,
                                   MaintenanceStaff staff,
                                   Equipment equipment)
    {

        maintenance.setMaintenanceStaff(staff);
        maintenance.setEquipment(equipment);
        maintenance.setScheduledDate(request.getScheduledDate());
        maintenance.setStartDate(request.getStartDate());
        maintenance.setStartTime(request.getStartTime());
        maintenance.setDeliveryDate(request.getDeliveryDate());
        maintenance.setTimeUsed(request.getTimeUsed());
        maintenance.setRegistrationDate(request.getRegistrationDate());
        maintenance.setDateOfCompletion(request.getDateOfCompletion());
        maintenance.setType(request.getActivity());
        maintenance.setObservations(request.getObservations());
        maintenance.setStaffObservations(request.getStaffObservations());
        maintenance.setDone(request.isDone());
    }
}
