package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.MaintenanceStaffDTO;
import com.imat.oncomedica.inventory_management.application.service.MaintenanceStaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/maintenance-staff")
public class MaintenanceStaffController {

    public MaintenanceStaffController(MaintenanceStaffService maintenanceStaffService) {
        this.maintenanceStaffService = maintenanceStaffService;
    }

    private final MaintenanceStaffService maintenanceStaffService;

    @PostMapping("/create")
    public ResponseEntity<MaintenanceStaffDTO> save(@RequestBody MaintenanceStaffDTO maintenanceStaffDTO){
        return ResponseEntity.created(URI.create("/api/maintenance-staff/" + maintenanceStaffDTO.getId()))
                .body(maintenanceStaffService.save(maintenanceStaffDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceStaffDTO>> findAll(){
        return ResponseEntity.ok().body(maintenanceStaffService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceStaffDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(maintenanceStaffService.getById(id));
    }
}
