package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceStaffService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceStaffServiceImpl implements MaintenanceStaffService {

    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public MaintenanceStaffServiceImpl(MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    @Override
    public MaintenanceStaffResponse getMaintenanceStaffByName(String name) {
        var maintenanceStaff = maintenanceStaffRepository.findByFirstName(name);
        return maintenanceStaffMapper.toMaintenanceStaffResponse(maintenanceStaff);
    }

    @Override
    public List<MaintenanceStaffResponse> getAllMaintenanceStaff() {
        List<MaintenanceStaff> maintenanceStaffList = maintenanceStaffRepository.findAll();
        return maintenanceStaffMapper.toMaintenanceStaffResponseList(maintenanceStaffList);
    }

    @Override
    public MaintenanceStaffResponse createMaintenanceStaff(CreateMaintenanceStaffRequest request) {
        MaintenanceStaff ms = maintenanceStaffMapper.toMaintenanceStaff(request);

        if (ms == null){
            throw new MaintenanceStaffNotFound();
        }

        maintenanceStaffRepository.save(ms);

        return maintenanceStaffMapper.toMaintenanceStaffResponse(ms);
    }

    @Override
    public MaintenanceStaffResponse updateMaintenanceStaff(UpdateMaintenanceStaffRequest maintenanceStaff, Integer id) {

        var maintenanceStaffSaved = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        if (maintenanceStaffSaved == null){
            throw new MaintenanceStaffNotFound();
        }

        var maintenanceStaffUpdated = maintenanceStaffMapper.updateMaintenanceStaff(maintenanceStaff);
        maintenanceStaffRepository.save(maintenanceStaffUpdated);
        return maintenanceStaffMapper.toMaintenanceStaffResponse(maintenanceStaffSaved);
    }

    @Override
    public MaintenanceStaffResponse getMaintenanceStaffById(Integer id) {
        MaintenanceStaff maintenanceStaff = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        return maintenanceStaffMapper.toMaintenanceStaffResponse(maintenanceStaff);
    }
}
