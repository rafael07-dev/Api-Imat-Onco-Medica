package com.imat.oncomedica.inventory_management.service;

import com.imat.oncomedica.inventory_management.Models.Equipment;
import com.imat.oncomedica.inventory_management.Models.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.Models.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.dto.MaintenanceScheduleDTO;
import com.imat.oncomedica.inventory_management.dto.MonthlyMaintenanceDTO;
import com.imat.oncomedica.inventory_management.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.repository.MonthlyMaintenanceRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MaintenanceScheduleService {

    private final EquipmentRepository equipmentRepository;
    private final MaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceScheduleMapper maintenanceScheduleMapper;
    private final MonthlyMaintenanceRepository monthlyMaintenanceRepository;

    /*
    * An array of months to compare
    */
    private static final String [] MONTHS = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    public MaintenanceScheduleService(EquipmentRepository equipmentRepository, MaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceScheduleMapper maintenanceScheduleMapper, MonthlyMaintenanceRepository monthlyMaintenanceRepository) {
        this.equipmentRepository = equipmentRepository;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.monthlyMaintenanceRepository = monthlyMaintenanceRepository;
    }

    public MaintenanceScheduleDTO addSchedule(MaintenanceScheduleDTO dto) {
        Equipment equipmentSaved = equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        MaintenanceSchedule maintenanceSchedule = setMaintenanceScheduleFromEquipmentData(equipmentSaved);

        maintenanceScheduleRepository.save(maintenanceSchedule);

        for (MonthlyMaintenanceDTO monthlyMaintenanceDTO : dto.getMonthlyMaintenances()){
            MonthlyMaintenance monthlyMaintenance = new MonthlyMaintenance();

            for (String month : MONTHS) {
                if (!monthlyMaintenanceDTO.getMonth().equalsIgnoreCase(month)){
                    MonthlyMaintenance monthMaintenance = new MonthlyMaintenance();

                    Map<String, Integer> maintenances = new HashMap<>();
                    maintenances.put("N/A", 0);

                    monthMaintenance.setMonth(month);
                    monthMaintenance.setMaintenanceType(maintenances);
                    monthMaintenance.setMaintenanceSchedule(maintenanceSchedule);
                    monthlyMaintenanceRepository.save(monthMaintenance);
                }
            }

            monthlyMaintenance.setMonth(monthlyMaintenanceDTO.getMonth());
            monthlyMaintenance.setMaintenanceType(monthlyMaintenanceDTO.getMaintenanceType());
            monthlyMaintenance.setMaintenanceSchedule(maintenanceSchedule);

            monthlyMaintenanceRepository.save(monthlyMaintenance);
        }

        return maintenanceScheduleMapper.toMaintenanceScheduleDTO(maintenanceSchedule);
    }

    public List<Map<String, Object>> findAllSchedules() {
        var maintenanceSchedules = maintenanceScheduleRepository.findAll();

        return maintenanceSchedules.stream()
                .map(schedule -> {
                    Map<String, Object> data = new HashMap<>();

                    Map<String, Object> equipmentData = getEquipmentDataFromMaintenanceSchedule(schedule);
                    List<Map<String, Object>> maintenances = getMaintenanceDataFromMaintenanceSchedule(schedule);

                    data.put("id", schedule.getId());
                    data.put("equipment", equipmentData);
                    data.put("maintenances", maintenances);
                    return data;
                }).toList();
    }

    public List<Map<String, Object>> findOneSchedule(Integer id) {
        var maintenanceSchedules = maintenanceScheduleRepository.findById(id);

        return maintenanceSchedules.stream()
                .map(schedule -> {
                    Map<String, Object> data = new HashMap<>();

                    Map<String, Object> equipmentData = getEquipmentDataFromMaintenanceSchedule(schedule);
                    List<Map<String, Object>> maintenances = getMaintenanceDataFromMaintenanceSchedule(schedule);

                    data.put("id", schedule.getId());
                    data.put("equipment", equipmentData);
                    data.put("maintenances", maintenances);
                    return data;
                }).toList();
    }

    public MaintenanceScheduleDTO deleteSchedule(Integer id) {

        Optional<MaintenanceSchedule> maintenanceScheduleSaved = Optional.ofNullable(maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance schedule not found")));

        maintenanceScheduleSaved.ifPresent(maintenanceScheduleRepository::delete);

        return maintenanceScheduleMapper.toMaintenanceScheduleDTO(maintenanceScheduleSaved.orElse(null));
    }

    public MaintenanceScheduleDTO updateSchedule(MaintenanceScheduleDTO dto, Integer id) {
        MaintenanceSchedule maintenanceScheduleSaved = maintenanceScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance schedule not found"));

        List<MonthlyMaintenance> monthlyMaintenanceSaved = maintenanceScheduleSaved.getMonthlyMaintenances();

        for (MonthlyMaintenance monthlyMaintenance: monthlyMaintenanceSaved){
            for (MonthlyMaintenanceDTO monthlyMaintenanceDTO: dto.getMonthlyMaintenances()){
                if (monthlyMaintenance.getMonth().equalsIgnoreCase(monthlyMaintenanceDTO.getMonth())){
                    monthlyMaintenance.setMaintenanceType(monthlyMaintenanceDTO.getMaintenanceType());

                    maintenanceScheduleRepository.save(maintenanceScheduleSaved);
                }
            }
        }

        return maintenanceScheduleMapper.toMaintenanceScheduleDTO(maintenanceScheduleSaved);
    }

    private static Map<String, Object> getEquipmentDataFromMaintenanceSchedule(MaintenanceSchedule schedule) {
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("id", schedule.getEquipment().getId());
        equipmentData.put("name", schedule.getEquipment().getEquipmentName());
        equipmentData.put("brand", schedule.getBrand());
        equipmentData.put("model", schedule.getModel());
        equipmentData.put("inventoryCode", schedule.getInventoryCode());
        equipmentData.put("area", schedule.getArea());
        equipmentData.put("floor", schedule.getFloor());
        equipmentData.put("location", schedule.getLocation());
        equipmentData.put("tower", schedule.getTower());
        equipmentData.put("type", schedule.getType());
        return equipmentData;
    }

    private static List<Map<String, Object>> getMaintenanceDataFromMaintenanceSchedule(MaintenanceSchedule schedule) {

        return schedule.getMonthlyMaintenances()
                .stream()
                .map(monthlyMaintenance -> {
                    Map<String, Object> maintenanceData = new HashMap<>();

                    maintenanceData.put("month", monthlyMaintenance.getMonth());
                    maintenanceData.put("maintenance_type", monthlyMaintenance.getMaintenanceType());
                    return maintenanceData;
                }).toList();
    }

    private static MaintenanceSchedule setMaintenanceScheduleFromEquipmentData(Equipment equipmentSaved){
        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
        maintenanceSchedule.setEquipment(equipmentSaved);
        maintenanceSchedule.setEquipmentName(equipmentSaved.getEquipmentName());
        maintenanceSchedule.setBrand(equipmentSaved.getBrand());
        maintenanceSchedule.setModel(equipmentSaved.getModel());
        maintenanceSchedule.setInventoryCode(equipmentSaved.getInventoryCode());
        maintenanceSchedule.setArea(equipmentSaved.getArea());
        maintenanceSchedule.setFloor(equipmentSaved.getFloor());
        maintenanceSchedule.setLocation(equipmentSaved.getLocation());
        maintenanceSchedule.setTower(equipmentSaved.getTower());
        maintenanceSchedule.setType(equipmentSaved.getType());

        return maintenanceSchedule;
    }
}
