package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceScheduleService;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.repository.SpringDataMaintenanceScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MaintenanceScheduleServiceImpl {

    /*private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;

    public MaintenanceScheduleServiceImpl(MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
    }


    @Override
    public List<MaintenanceScheduleResponse> getAllMaintenanceSchedules() {
        List<MaintenanceSchedule> maintenanceSchedules = ;

        return maintenanceSchedules.stream()
                .map(maintenanceScheduleMapper::buildMaintenanceScheduleResponse)
                .toList();
    }

    @Override
    public MaintenanceScheduleResponse getMaintenanceScheduleById(Integer id) {
        var maintenanceSchedule = springDataMaintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        return maintenanceScheduleMapper.buildMaintenanceScheduleResponse(maintenanceSchedule);
    }

    @Override
    public List<MaintenanceScheduleResponse> getMaintenanceScheduleByYear(Integer year) {
        var maintenanceScheduleList = springDataMaintenanceScheduleRepository.findByYear(year);

        if (maintenanceScheduleList.isEmpty())
            throw new MaintenanceScheduleNotFoundException("there is no maintenances for this year");

        var copyMaintenanceScheduleList = maintenanceScheduleList
                .stream()
                .map(maintenanceScheduleMapper::buildMaintenanceScheduleResponse)
                .toList();

        return copyMaintenanceScheduleList.stream()
                .map(schedule -> {
                    var filteredMonthlyMaintenance = schedule.getMonthlyMaintenances()
                            .stream()
                            .filter(maintenanceSchedule -> maintenanceSchedule.getYear().equals(year))
                            .toList();

                    schedule.setMonthlyMaintenances(filteredMonthlyMaintenance);
                    return schedule;
                }).toList();
    }

    @Override
    public String deleteMaintenanceSchedule(Integer id) {
        var maintenanceSchedule = springDataMaintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new MaintenanceScheduleNotFoundException(id));

        springDataMaintenanceScheduleRepository.delete(maintenanceSchedule);
        return "Maintenance schedule deleted";
    }*/
}
