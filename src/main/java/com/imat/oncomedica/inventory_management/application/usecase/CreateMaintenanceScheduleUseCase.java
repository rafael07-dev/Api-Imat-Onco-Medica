package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenanceType;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.factory.MaintenanceScheduleFactory;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateMaintenanceScheduleUseCase {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceScheduleFactory maintenanceScheduleFactory;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;

    public CreateMaintenanceScheduleUseCase(EquipmentRepository equipmentRepository,
                                            MaintenanceScheduleRepository maintenanceScheduleRepository,
                                            MaintenanceStaffRepository maintenanceStaffRepository,
                                            MaintenanceScheduleFactory maintenanceScheduleFactory,
                                            MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceScheduleFactory = maintenanceScheduleFactory;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }


    public MaintenanceScheduleResponse execute(CreateMaintenanceScheduleRequest request) {

        final Integer EQUIPMENT_ID = request.getEquipmentId();
        final Integer STAFF_ID = request.getMaintenanceStaffId();

        var equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(EQUIPMENT_ID));

        var staff = maintenanceStaffRepository.findById(request.getMaintenanceStaffId())
                .orElseThrow(() -> new MaintenanceStaffNotFound(STAFF_ID));

        var maintenanceSchedule = maintenanceScheduleFactory.create(equipment, staff);

        List<MonthlyMaintenance> monthlyMaintenances = new ArrayList<>();

        request.getMonthlyMaintenances()
                .forEach(m -> {
                    var monthlyMaintenance = new MonthlyMaintenance();
                    monthlyMaintenance.setMonth(m.getMonth());
                    monthlyMaintenance.setYear(m.getYear());
                    monthlyMaintenance.setMaintenanceSchedule(maintenanceSchedule);

                    var monthlyMaintenanceType = new ArrayList<MonthlyMaintenanceType>();
                    m.getMaintenanceType()
                            .forEach(mt -> {
                                var mmt = new MonthlyMaintenanceType();

                                var name = mt.getMaintenanceType().toUpperCase();

                                mmt.setMaintenanceTypeEnum(MaintenanceTypeEnum.valueOf(name));
                                mmt.setQuantity(mt.getQuantity());

                                mmt.setMonthlyMaintenance(monthlyMaintenance);
                                monthlyMaintenanceType.add(mmt);
                            });

                    monthlyMaintenance.setMaintenanceTypes(monthlyMaintenanceType);
                    monthlyMaintenances.add(monthlyMaintenance);
                });
        maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenances);

        var maintenanceSaved = maintenanceScheduleRepository.save(maintenanceSchedule);

        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceSaved);
    }
}
