package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceScheduleService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MaintenanceScheduleServiceImpl implements MaintenanceScheduleService {

    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    /*
    * An array of months to compare
    */
    private static final String [] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    public MaintenanceScheduleServiceImpl(MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }


    @Override
    public List<MaintenanceScheduleResponse> getAllMaintenanceSchedules() {
        List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleRepository.findAll();

        return maintenanceSchedules.stream()
                .map(m -> maintenanceScheduleMapper.buildMaintenanceScheduleResponse(m))
                .toList();
    }

    @Override
    public MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id) {
        var maintenanceSchedule = maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceSchedule);
    }

    @Override
    public MaintenanceScheduleResponse updateMaintenanceSchedule(UpdateMaintenanceScheduleRequest maintenanceSchedule, Integer id) {
        return null;
    }

    @Override
    public String deleteMaintenanceSchedule(Integer id) {
        var maintenanceSchedule = maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        maintenanceScheduleRepository.delete(maintenanceSchedule);
        return "Maintenance schedule deleted";
    }
}
