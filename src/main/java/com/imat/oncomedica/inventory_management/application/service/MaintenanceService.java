package com.imat.oncomedica.inventory_management.application.service;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.Maintenance;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceDTO;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final EquipmentRepository equipmentRepository;
    private final MaintenanceStaffRepository maintenanceStaffRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, EquipmentRepository equipmentRepository, MaintenanceStaffRepository maintenanceStaffRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.equipmentRepository = equipmentRepository;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public MaintenanceDTO save(MaintenanceDTO maintenance){
        Maintenance newMaintenance = new Maintenance();
        var equipmentId = maintenance.getEquipmentId();
        var maintenanceStaffId = maintenance.getMaintenanceStaffId();

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("equipment not found"));

        MaintenanceStaff maintenanceStaff = maintenanceStaffRepository.findById(maintenanceStaffId)
                        .orElseThrow(() -> new RuntimeException("Maintenance staff not found"));

        newMaintenance.setEquipment(equipment);
        newMaintenance.setInventoryCode(equipment.getInventoryCode());
        newMaintenance.setMaintenanceStaff(maintenanceStaff);
        newMaintenance.setScheduledDate(maintenance.getScheduledDate());
        newMaintenance.setStartDate(maintenance.getStartDate());
        newMaintenance.setStartTime(maintenance.getStartTime());
        newMaintenance.setDeliveryDate(maintenance.getDeliveryDate());
        newMaintenance.setTimeUsed(maintenance.getTimeUsed());
        newMaintenance.setRegistrationDate(maintenance.getRegistrationDate());
        newMaintenance.setDateOfCompletion(maintenance.getDateOfCompletion());
        newMaintenance.setType(maintenance.getType());
        newMaintenance.setObservations(maintenance.getObservations());

        var maintenanceSaved = maintenanceRepository.save(newMaintenance);

        return maintenanceMapper.toMaintenanceDTO(maintenanceSaved);
    }

    public List<MaintenanceDTO> findAll(){

        List<Maintenance> maintenances = maintenanceRepository.findAll();

        return maintenanceMapper.toMaintenanceDtoList(maintenances);
    }

    public List<Maintenance> getMaintenancesByEquipmentId(Integer equipmentId) {
        return maintenanceRepository.findByEquipmentId(equipmentId);
    }

    public List<Maintenance> getMaintenancesByMaintenanceStaffId(Integer maintenanceStaffId) {
        return maintenanceRepository.findByEquipmentId(maintenanceStaffId);
    }
}
