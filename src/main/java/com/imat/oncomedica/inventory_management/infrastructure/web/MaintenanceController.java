package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.domain.entity.Maintenance;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceDTO;
import com.imat.oncomedica.inventory_management.application.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceDTO>> findAll(){
        return ResponseEntity.ok().body(maintenanceService.findAll());
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Maintenance>> getMaintenancesByEquipmentId(@PathVariable Integer equipmentId) {
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByEquipmentId(equipmentId);
        if (maintenances.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/{maintenanceStaffId}")
    public ResponseEntity<List<Maintenance>> getMaintenancesByMaintenanceStaffId(@PathVariable Integer maintenanceStaffId) {
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByMaintenanceStaffId(maintenanceStaffId);
        if (maintenances.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenances);
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceDTO> save(@RequestBody MaintenanceDTO maintenance){
        return ResponseEntity.created(URI.create("/api/maintenances/" + maintenance.getId()))
                .body(maintenanceService.save(maintenance));
    }
}
