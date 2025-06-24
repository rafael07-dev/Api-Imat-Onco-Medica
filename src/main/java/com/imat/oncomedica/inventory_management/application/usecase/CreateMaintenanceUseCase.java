package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.entity.Order;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.NotificationService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;

import java.time.LocalDateTime;

public class CreateMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final EquipmentRepository equipmentRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;

    public CreateMaintenanceUseCase(MaintenanceRepository maintenanceRepository, EquipmentRepository equipmentRepository, MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceMapper maintenanceMapper, NotificationService notificationService, OrderRepository orderRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.equipmentRepository = equipmentRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public MaintenanceResponse execute(CreateMaintenanceRequest request) {
        var equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(request.getEquipmentId()));

        var staff = maintenanceStaffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new MaintenanceStaffNotFound(request.getEquipmentId()));

        var maintenace = maintenanceMapper.toMaintenance(request, equipment, staff);

        var maintenanceResponse = maintenanceRepository.save(maintenace);

        notificationService.notifyMaintenanceAssigned(maintenanceResponse);

        Order order = new Order();
        order.setCreationDate(LocalDateTime.now());
        order.setEquipment(equipment);
        order.setMaintenance(maintenace);
        order.setMaintenanceStaff(staff);

        orderRepository.save(order);

        return maintenanceMapper.toMaintenanceResponse(maintenanceResponse);
    }
}
