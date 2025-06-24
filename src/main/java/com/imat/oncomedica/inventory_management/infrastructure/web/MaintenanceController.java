package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.usecase.CreateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.UpdateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final CreateMaintenanceUseCase createMaintenanceUseCase;
    private final UpdateMaintenanceUseCase updateMaintenanceUseCase;

    public MaintenanceController(MaintenanceService maintenanceService, CreateMaintenanceUseCase createMaintenanceUseCase, UpdateMaintenanceUseCase updateMaintenanceUseCase) {
        this.maintenanceService = maintenanceService;
        this.createMaintenanceUseCase = createMaintenanceUseCase;
        this.updateMaintenanceUseCase = updateMaintenanceUseCase;
    }


    @GetMapping("/")
    public ResponseEntity<List<MaintenanceResponse>> findAll(){
        return ResponseEntity.ok().body(maintenanceService.getAllMaintenances());
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<MaintenanceResponse>> getMaintenancesByEquipmentId(@PathVariable Integer equipmentId) {
        List<MaintenanceResponse> maintenances = maintenanceService.getMaintenanceByEquipmentID(equipmentId);
        if (maintenances.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/{maintenanceStaffId}")
    public ResponseEntity<List<MaintenanceResponse>> getMaintenancesByMaintenanceStaffId(@PathVariable Integer maintenanceStaffId) {
        List<MaintenanceResponse> maintenances = maintenanceService.getEquipmentByStaffId(maintenanceStaffId);
        if (maintenances.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(maintenances);
    }

    @PutMapping("/{staffId}/update/{maintenanceId}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable Integer maintenanceId,
                                                                 @PathVariable Integer staffId,
                                                                 @RequestBody UpdateMaintenanceRequest request) {
        MaintenanceResponse response = updateMaintenanceUseCase.execute(maintenanceId, staffId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceResponse> save(@RequestBody CreateMaintenanceRequest request){
        var maintenanceResponse = createMaintenanceUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/maintenances/" + maintenanceResponse.getId()))
                .body(maintenanceResponse);
    }

    @PostMapping("/upload-image/{maintenanceId}")
    public ResponseEntity<String> save(@RequestBody MultipartFile file, @PathVariable Integer maintenanceId){

    }
}
