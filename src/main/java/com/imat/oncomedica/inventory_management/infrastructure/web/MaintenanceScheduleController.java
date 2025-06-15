package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.MaintenanceScheduleDTO;
import com.imat.oncomedica.inventory_management.application.service.MaintenanceScheduleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
public class MaintenanceScheduleController {

    private final MaintenanceScheduleServiceImpl maintenanceScheduleServiceImpl;

    public MaintenanceScheduleController(MaintenanceScheduleServiceImpl maintenanceScheduleServiceImpl) {
        this.maintenanceScheduleServiceImpl = maintenanceScheduleServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getAllMaintenanceSchedules() {
        return ResponseEntity.ok().body(maintenanceScheduleServiceImpl.findAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Map<String, Object>>> getAllMaintenanceSchedules(@PathVariable Integer id) {
        return ResponseEntity.ok().body(maintenanceScheduleServiceImpl.findOneSchedule(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceScheduleDTO> save(@RequestBody MaintenanceScheduleDTO maintenanceScheduleDTO) {
        return ResponseEntity.created(URI.create("/api/schedule/" + maintenanceScheduleDTO.getId()))
                .body(maintenanceScheduleServiceImpl.addSchedule(maintenanceScheduleDTO));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MaintenanceScheduleDTO> update(@RequestBody MaintenanceScheduleDTO dto, @PathVariable Integer id) {
        return ResponseEntity.ok(maintenanceScheduleServiceImpl.updateSchedule(dto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MaintenanceScheduleDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(maintenanceScheduleServiceImpl.deleteSchedule(id));
    }
}
