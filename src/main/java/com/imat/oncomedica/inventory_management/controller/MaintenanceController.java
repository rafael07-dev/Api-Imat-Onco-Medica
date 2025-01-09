package com.imat.oncomedica.inventory_management.controller;

import com.imat.oncomedica.inventory_management.Models.Maintenance;
import com.imat.oncomedica.inventory_management.dto.MaintenanceDTO;
import com.imat.oncomedica.inventory_management.service.MaintenanceService;
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
    public ResponseEntity<List<Maintenance>> findAll(){
        return ResponseEntity.ok().body(maintenanceService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceDTO> save(@RequestBody MaintenanceDTO maintenance){
        return ResponseEntity.created(URI.create("/api/maintenances/" + maintenance.getId()))
                .body(maintenanceService.save(maintenance));
    }
}
