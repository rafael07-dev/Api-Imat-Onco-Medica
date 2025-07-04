package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.CreateOrderUseCase;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.NotificationService;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;

public class CreateMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final EquipmentRepository equipmentRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final NotificationService notificationService;
    private final CreateOrderUseCase createOrderUseCase;

    public CreateMaintenanceUseCase(MaintenanceRepository maintenanceRepository, EquipmentRepository equipmentRepository, MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceMapper maintenanceMapper, NotificationService notificationService, CreateOrderUseCase createOrderUseCase) {
        this.maintenanceRepository = maintenanceRepository;
        this.equipmentRepository = equipmentRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.notificationService = notificationService;
        this.createOrderUseCase = createOrderUseCase;
    }


    public MaintenanceResponse execute(CreateMaintenanceRequest request) {
        var equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(request.getEquipmentId()));

        var staff = maintenanceStaffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new MaintenanceStaffNotFound(request.getEquipmentId()));

        var maintenance = maintenanceMapper.toMaintenance(request, equipment, staff);

        var maintenanceResponse = maintenanceRepository.save(maintenance);

        createOrderUseCase.execute(maintenance, equipment, staff);

        notificationService.notifyMaintenanceAssigned(maintenanceResponse);

        return maintenanceMapper.toMaintenanceResponse(maintenanceResponse);
    }
}
