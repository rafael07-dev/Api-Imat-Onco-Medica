package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.Maintenance;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.entity.OrderStatus;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;

public class UpdateMaintenanceUseCase {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final EquipmentRepository equipmentRepository;
    private final OrderRepository orderRepository;
    private final OrderPdfGenerator orderPdfGenerator;

    public UpdateMaintenanceUseCase(MaintenanceRepository maintenanceRepository,
                                    MaintenanceMapper maintenanceMapper,
                                    MaintenanceStaffRepository maintenanceStaffRepository,
                                    EquipmentRepository equipmentRepository, OrderRepository orderRepository,
                                    OrderPdfGenerator orderPdfGenerator)
    {

        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.equipmentRepository = equipmentRepository;
        this.orderRepository = orderRepository;
        this.orderPdfGenerator = orderPdfGenerator;
    }


    public MaintenanceResponse execute(Integer maintenanceId, Integer staffId, UpdateMaintenanceRequest request){

        var maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(maintenanceId));

        var staff = maintenanceStaffRepository.findById(staffId)
                .orElseThrow(() -> new MaintenanceStaffNotFound(staffId));

        var equipment = equipmentRepository.findById(maintenance.getEquipment().getId())
                .orElseThrow(() -> new EquipmentNotFoundException(maintenance.getEquipment().getId()));

        updateMaintenance(maintenance, request, staff, equipment);

        staff.setMaintenanceCompleted(staff.getMaintenanceCompleted() + 1);
        maintenanceStaffRepository.save(staff);

        var maintenanceSaved = maintenanceRepository.save(maintenance);

        var order = orderRepository.findByMaintenance_Id(maintenanceId);

        order.setStatus(OrderStatus.COMPLETED);
        orderPdfGenerator.generateOderReport(order);

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
