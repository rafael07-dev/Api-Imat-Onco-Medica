package com.imat.oncomedica.inventory_management.application.usecase.maintenance;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.exception.OrderNotFoundException;
import com.imat.oncomedica.inventory_management.domain.model.OrderStatus;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.domain.repository.OrderRepository;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;

public class CompleteMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final OrderPdfGenerator orderPdfGenerator;
    private final OrderRepository orderRepository;

    public CompleteMaintenanceUseCase(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper, OrderPdfGenerator orderPdfGenerator, OrderRepository orderRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
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


        var order = orderRepository.getOrderByMaintenanceId(maintenanceId)
                .orElseThrow(() -> new OrderNotFoundException(maintenanceId));

        order.setStatus(OrderStatus.COMPLETED);

        var orderSaved = orderRepository.saveOrder(order);

        orderPdfGenerator.generateOderReport(orderSaved);

        return maintenanceMapper.toMaintenanceResponse(maintenanceSaved);

    }
}
