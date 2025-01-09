package com.imat.oncomedica.inventory_management.service;

import com.imat.oncomedica.inventory_management.Models.MaintenanceStaff;
import com.imat.oncomedica.inventory_management.dto.MaintenanceStaffDTO;
import com.imat.oncomedica.inventory_management.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceStaffService {

    private final MaintenanceStaffRepository maintenanceStaffRepository;

    public MaintenanceStaffService(MaintenanceStaffRepository maintenanceStaffRepository) {
        this.maintenanceStaffRepository = maintenanceStaffRepository;
    }

    public MaintenanceStaffDTO save(MaintenanceStaffDTO maintenanceStaffDTO){
        MaintenanceStaff maintenanceStaff = new MaintenanceStaff();
        maintenanceStaff.setFirstName(maintenanceStaffDTO.getName());
        maintenanceStaff.setLastName(maintenanceStaffDTO.getLastName());
        maintenanceStaff.setOccupation(maintenanceStaffDTO.getOcupation());
        maintenanceStaff.setAvailability(maintenanceStaffDTO.isAvailability());
        maintenanceStaff.setMaintenanceCompleted(maintenanceStaffDTO.getMaintenanceCompleted());

        maintenanceStaffRepository.save(maintenanceStaff);
        maintenanceStaffDTO.setId(maintenanceStaff.getId());

        return maintenanceStaffDTO;
    }

    public MaintenanceStaffDTO getById(Integer id){
        MaintenanceStaff maintenanceStaff = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new MaintenanceStaffDTO(maintenanceStaff.getId(),
                maintenanceStaff.getFirstName(),
                maintenanceStaff.getLastName(),
                maintenanceStaff.getOccupation(),
                maintenanceStaff.isAvailability(),
                maintenanceStaff.getMaintenanceCompleted());
    }

    public List<MaintenanceStaffDTO> findAll(){
        List<MaintenanceStaff> maintenanceStaffList = maintenanceStaffRepository.findAll();

        List<MaintenanceStaffDTO> maintenanceStaffDTOList = maintenanceStaffList.stream()
                .map((maintenanceStaff) -> new MaintenanceStaffDTO(
                        maintenanceStaff.getId(),
                        maintenanceStaff.getFirstName(),
                        maintenanceStaff.getLastName(),
                        maintenanceStaff.getOccupation(),
                        maintenanceStaff.isAvailability(),
                        maintenanceStaff.getMaintenanceCompleted()
                ))
                .toList();
        return maintenanceStaffDTOList;
    }
}
