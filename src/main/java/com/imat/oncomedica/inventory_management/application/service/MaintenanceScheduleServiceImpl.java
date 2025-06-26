package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.*;
import com.imat.oncomedica.inventory_management.application.mapper.MonthlyMaintenanceTypeMapper;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenanceType;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceScheduleService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceTypeRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MaintenanceScheduleServiceImpl implements MaintenanceScheduleService {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MonthlyMaintenanceTypeMapper monthlyMaintenanceTypeMapper;
    private final MaintenanceTypeRepository maintenanceTypeRepository;
    /*
    * An array of months to compare
    */
    private static final String [] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    public MaintenanceScheduleServiceImpl(EquipmentRepository equipmentRepository, MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper, MaintenanceStaffRepository maintenanceStaffRepository, MonthlyMaintenanceTypeMapper monthlyMaintenanceTypeMapper, MaintenanceTypeRepository maintenanceTypeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.monthlyMaintenanceTypeMapper = monthlyMaintenanceTypeMapper;
        this.maintenanceTypeRepository = maintenanceTypeRepository;
    }


    @Override
    public List<MaintenanceScheduleResponse> getAllMaintenanceSchedules() {
        List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleRepository.findAll();

        return maintenanceSchedules.stream()
                .map(m -> maintenanceScheduleMapper.toMaintenanceScheduleResponse(m))
                .toList();
    }

    @Override
    public MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id) {
        var maintenanceSchedule = maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        return maintenanceScheduleMapper.toMaintenanceScheduleResponse(maintenanceSchedule);
    }

    @Override
    public MaintenanceScheduleResponse updateMaintenanceSchedule(UpdateMaintenanceScheduleRequest maintenanceSchedule, Integer id) {
        return null;
    }

    @Override
    public MaintenanceScheduleResponse createMaintenanceSchedule(CreateMaintenanceScheduleRequest request) {
        var equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(request.getEquipmentId()));

        var maintenanceStaff = maintenanceStaffRepository.findById(request.getMaintenanceStaffId())
                .orElseThrow(() -> new MaintenanceStaffNotFound(request.getMaintenanceStaffId()));

        var maintenanceSchedule = new MaintenanceSchedule();

        maintenanceSchedule.setEquipment(equipment);
        maintenanceSchedule.setResponsible(maintenanceStaff);
        maintenanceSchedule.setMonthlyMaintenances(new ArrayList<>());

        List<MonthlyMaintenance> monthlyMaintenances = new ArrayList<>();

        request.getMonths()
                .stream()
                .forEach(m -> {
                    var monthlyMaintenance = new MonthlyMaintenance();
                    monthlyMaintenance.setMonth(m.getMonth());
                    monthlyMaintenance.setYear(m.getYear());
                    monthlyMaintenance.setMaintenanceSchedule(maintenanceSchedule);

                    var monthlyMaintenanceType = new ArrayList<MonthlyMaintenanceType>();
                    m.getMaintenanceType()
                            .stream()
                            .forEach(mt -> {
                                var mmt = new MonthlyMaintenanceType();

                                var name = mt.getMaintenanceType().getName();
                                var maintenanceType = maintenanceTypeRepository.findByName(name);

                                mmt.setMaintenanceType(maintenanceType);
                                mmt.setQuantity(mt.getQuantity());

                                mmt.setMonthlyMaintenance(monthlyMaintenance);
                                monthlyMaintenanceType.add(mmt);
                            });

                    monthlyMaintenance.setMaintenanceTypes(monthlyMaintenanceType);
                    monthlyMaintenances.add(monthlyMaintenance);
                });
        maintenanceSchedule.setMonthlyMaintenances(monthlyMaintenances);

        var maintenanceSaved = maintenanceScheduleRepository.save(maintenanceSchedule);

        return maintenanceScheduleMapper.toMaintenanceScheduleResponse(maintenanceSaved);
    }

    @Override
    public String deleteMaintenanceSchedule(Integer id) {
        var maintenanceSchedule = maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        maintenanceScheduleRepository.delete(maintenanceSchedule);
        return "Maintenance schedule deleted";
    }
}
