package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.*;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceScheduleService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MonthlyMaintenanceRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MaintenanceScheduleServiceImpl implements MaintenanceScheduleService {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    private final MonthlyMaintenanceRepository monthlyMaintenanceRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    /*
    * An array of months to compare
    */
    private static final String [] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    public MaintenanceScheduleServiceImpl(EquipmentRepository equipmentRepository, MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper, MonthlyMaintenanceRepository monthlyMaintenanceRepository, MaintenanceStaffRepository maintenanceStaffRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.monthlyMaintenanceRepository = monthlyMaintenanceRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
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

        var maintenanceList = maintenanceSchedule.getMonthlyMaintenances();

        request.getMonths()
                .stream()
                .forEach(month -> {
                    var mm = new MonthlyMaintenance();

                    mm.setMonth(month.getMonth());
                    mm.setMaintenanceType(month.getMaintenanceType());
                    mm.setMaintenanceSchedule(maintenanceSchedule);

                    maintenanceList.add(mm);
                });

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
