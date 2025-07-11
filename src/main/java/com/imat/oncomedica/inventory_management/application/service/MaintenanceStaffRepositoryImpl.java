package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.repository.SpringDataMaintenanceStaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceStaffRepositoryImpl implements MaintenanceStaffRepository {

    private final SpringDataMaintenanceStaffRepository springDataMaintenanceStaffRepository;
    private final MaintenanceStaffMapper maintenanceStaffMapper;

    public MaintenanceStaffRepositoryImpl(SpringDataMaintenanceStaffRepository springDataMaintenanceStaffRepository, MaintenanceStaffMapper maintenanceStaffMapper) {
        this.springDataMaintenanceStaffRepository = springDataMaintenanceStaffRepository;
        this.maintenanceStaffMapper = maintenanceStaffMapper;
    }

    @Override
    public MaintenanceStaffResponse getMaintenanceStaffByName(String name) {
        var maintenanceStaff = springDataMaintenanceStaffRepository.findByFirstName(name);
        return maintenanceStaffMapper.toMaintenanceStaffResponse(maintenanceStaff);
    }

    @Override
    public List<MaintenanceStaffResponse> getAllMaintenanceStaff() {
        List<MaintenanceStaff> maintenanceStaffList = springDataMaintenanceStaffRepository.findAll();
        return maintenanceStaffMapper.toMaintenanceStaffResponseList(maintenanceStaffList);
    }

    @Override
    public MaintenanceStaffResponse createMaintenanceStaff(CreateMaintenanceStaffRequest request) {
        MaintenanceStaff ms = maintenanceStaffMapper.to (request);

        if (ms == null){
            throw new MaintenanceStaffNotFound();
        }

        springDataMaintenanceStaffRepository.save(ms);

        return maintenanceStaffMapper.toMaintenanceStaffResponse(ms);
    }

    @Override
    public MaintenanceStaffResponse updateMaintenanceStaff(UpdateMaintenanceStaffRequest maintenanceStaff, Integer id) {

        var maintenanceStaffSaved =  .findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        if (maintenanceStaffSaved == null){
            throw new MaintenanceStaffNotFound();
        }

        var maintenanceStaffUpdated = maintenanceStaffMapper.updateMaintenanceStaff(maintenanceStaff, maintenanceStaffSaved);
        var staffSaved = springDataMaintenanceStaffRepository.save(maintenanceStaffUpdated);
        return maintenanceStaffMapper.toMaintenanceStaffResponse(staffSaved);
    }

    @Override
    public MaintenanceStaffResponse getMaintenanceStaffById(Integer id) {
        MaintenanceStaff maintenanceStaff = springDataMaintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        return maintenanceStaffMapper.toMaintenanceStaffResponse(maintenanceStaff);
    }
}
