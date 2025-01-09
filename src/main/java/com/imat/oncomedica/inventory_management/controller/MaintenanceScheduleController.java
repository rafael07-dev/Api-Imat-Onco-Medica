package com.imat.oncomedica.inventory_management.controller;

import com.imat.oncomedica.inventory_management.dto.MaintenanceScheduleDTO;
import com.imat.oncomedica.inventory_management.service.MaintenanceScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
public class MaintenanceScheduleController {

    private final MaintenanceScheduleService maintenanceScheduleService;

    public MaintenanceScheduleController(MaintenanceScheduleService maintenanceScheduleService) {
        this.maintenanceScheduleService = maintenanceScheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllMaintenanceSchedules() {
        return ResponseEntity.ok().body(maintenanceScheduleService.findAllSchedules());
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceScheduleDTO> save(@RequestBody MaintenanceScheduleDTO maintenanceScheduleDTO) {
        return ResponseEntity.created(URI.create("/api/schedule/" + maintenanceScheduleDTO.getId()))
                .body(maintenanceScheduleService.addSchedule(maintenanceScheduleDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MaintenanceScheduleDTO> update(@RequestBody MaintenanceScheduleDTO maintenanceScheduleDTO, @PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MaintenanceScheduleDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(maintenanceScheduleService.deleteSchedule(id));
    }
}
