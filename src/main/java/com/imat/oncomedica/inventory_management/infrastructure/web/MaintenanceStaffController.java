package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceStaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService maintenanceStaffService;

    public MaintenanceStaffController(MaintenanceStaffService maintenanceStaffService) {
        this.maintenanceStaffService = maintenanceStaffService;
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceStaffResponse> save(@RequestBody CreateMaintenanceStaffRequest request){
        MaintenanceStaffResponse maintenanceStaffSaved = maintenanceStaffService.createMaintenanceStaff(request);
        return ResponseEntity.created(URI.create("/api/maintenance-staff/" + maintenanceStaffSaved.getId()))
                .body(maintenanceStaffSaved);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<MaintenanceStaffResponse> updateStaff(@RequestBody UpdateMaintenanceStaffRequest request, @PathVariable Integer id){
        return ResponseEntity.ok().body(maintenanceStaffService.updateMaintenanceStaff(request, id));
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceStaffResponse>> findAll(){
        return ResponseEntity.ok().body(maintenanceStaffService.getAllMaintenanceStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceStaffResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(maintenanceStaffService.getMaintenanceStaffById(id));
    }
}
