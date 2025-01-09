package com.imat.oncomedica.inventory_management.mapper;

import com.imat.oncomedica.inventory_management.Models.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.dto.MaintenanceScheduleDTO;
import com.imat.oncomedica.inventory_management.repository.MaintenanceScheduleRepository;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MaintenanceScheduleMapper {

    List<MaintenanceScheduleDTO> maintenanceScheduleDTOS(List<MaintenanceSchedule> maintenanceSchedules);
    MaintenanceScheduleDTO toMaintenanceScheduleDTO(MaintenanceSchedule maintenanceSchedule);
    static MaintenanceSchedule toMaintenanceSchedule(MaintenanceScheduleDTO maintenanceScheduleDTO, MaintenanceScheduleRepository maintenanceScheduleRepository){

        return maintenanceScheduleRepository.findById(maintenanceScheduleDTO.getId())
                .orElseThrow(() -> new RuntimeException("Maintenance schedule not found"));
    }

    /*static List<MaintenanceSchedule> toMaintenanceScheduleList (List<MaintenanceScheduleDTO> maintenanceScheduleDTOS, EquipmentRepository equipmentRepository) {
        Equipment equipment = equipmentRepository.findById(maintenanceScheduleDTOS.getFirst().getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        return maintenanceScheduleDTOS.stream()
                .map(maintenanceScheduleDTO -> new MaintenanceSchedule(
                        maintenanceScheduleDTO.getId(),
                        equipment,

                ))
    }*/
}
