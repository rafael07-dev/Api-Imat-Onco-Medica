package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.entity.OrderStatus;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;

public class CompleteMaintenanceUseCase {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final EquipmentRepository equipmentRepository;
    private final OrderPdfGenerator orderPdfGenerator;
    private final OrderRepository orderRepository;

    public CompleteMaintenanceUseCase(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, EquipmentRepository equipmentRepository, OrderPdfGenerator orderPdfGenerator, OrderRepository orderRepository) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.equipmentRepository = equipmentRepository;
        this.orderPdfGenerator = orderPdfGenerator;
        this.orderRepository = orderRepository;
    }

    public MaintenanceResponse execute(UpdateMaintenanceRequest request, Integer maintenanceId) {

        var maintenanceEntity = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceStaffNotFound(maintenanceId));

        maintenanceEntity.setDone(true);
        maintenanceEntity.setStaffObservations(request.getStaffObservations());
        maintenanceEntity.setDeliveryTime(request.getDeliveryTime());
        maintenanceEntity.setStartTime(request.getStartTime());
        maintenanceEntity.setTimeUsed(request.getTimeUsed());
        maintenanceEntity.setDateOfCompletion( request.getDateOfCompletion());

        var maintenanceSaved = maintenanceRepository.save(maintenanceEntity);


        var order = orderRepository.findByMaintenance_Id(maintenanceId);

        order.setStatus(OrderStatus.COMPLETED);

        var orderSaved = orderRepository.save(order);

        orderPdfGenerator.generateOderReport(orderSaved);

        return maintenanceMapper.toMaintenanceResponse(maintenanceSaved);

    }
}
