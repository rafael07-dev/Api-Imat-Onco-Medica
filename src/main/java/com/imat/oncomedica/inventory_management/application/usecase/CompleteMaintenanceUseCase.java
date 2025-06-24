package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;

public class CompleteMaintenanceUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final EquipmentRepository equipmentRepository;

    public CompleteMaintenanceUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, EquipmentRepository equipmentRepository) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.equipmentRepository = equipmentRepository;
    }

    public MaintenanceResponse execute(UpdateMaintenanceRequest request, Integer staffId, Integer maintenanceId) {
        var staff = maintenanceStaffRepository.findById(staffId)
                .orElseThrow(() -> new MaintenanceStaffNotFound(staffId));

        var maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceStaffNotFound(maintenanceId));

        var equipment = equipmentRepository.findById(maintenance.getEquipment().getId())
                .orElseThrow(() -> new EquipmentNotFoundException(maintenance.getEquipment().getId()));

        var maintenanceRequest = maintenanceMapper.updateMaintenance(request, equipment, staff);



    }
}
