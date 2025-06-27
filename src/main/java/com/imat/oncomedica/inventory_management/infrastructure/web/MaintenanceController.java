package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.usecase.CompleteMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.CreateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.UpdateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.UploadMaintenanceImageUseCase;
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
    private final UploadMaintenanceImageUseCase uploadMaintenanceImageUseCase;
    private final UpdateMaintenanceUseCase updateMaintenanceUseCase;
    private final CompleteMaintenanceUseCase completeMaintenanceUseCase;

    public MaintenanceController(MaintenanceService maintenanceService, CreateMaintenanceUseCase createMaintenanceUseCase, UploadMaintenanceImageUseCase uploadMaintenanceImageUseCase, UpdateMaintenanceUseCase updateMaintenanceUseCase, CompleteMaintenanceUseCase completeMaintenanceUseCase) {
        this.maintenanceService = maintenanceService;
        this.createMaintenanceUseCase = createMaintenanceUseCase;
        this.uploadMaintenanceImageUseCase = uploadMaintenanceImageUseCase;
        this.updateMaintenanceUseCase = updateMaintenanceUseCase;
        this.completeMaintenanceUseCase = completeMaintenanceUseCase;
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

    @PutMapping("/update/{maintenanceId}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable Integer maintenanceId,
                                                                 @RequestBody UpdateMaintenanceRequest request) {
        MaintenanceResponse response = updateMaintenanceUseCase.execute(maintenanceId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/completed/{maintenanceId}")
    public ResponseEntity<MaintenanceResponse> maintenanceCompleted(@PathVariable Integer maintenanceId,
                                                                 @RequestBody UpdateMaintenanceRequest request) {
        MaintenanceResponse response = completeMaintenanceUseCase.execute(request, maintenanceId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceResponse> save(@RequestBody CreateMaintenanceRequest request){
        var maintenanceResponse = createMaintenanceUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/maintenances/" + maintenanceResponse.getId()))
                .body(maintenanceResponse);
    }

    @PostMapping(value = "/upload-image/{maintenanceId}", consumes = "multipart/form-data")
    public String updateMaintenanceImage(@RequestParam("file") MultipartFile file, @PathVariable Integer maintenanceId){
        return uploadMaintenanceImageUseCase.UploadMaintenanceImageUseCase(file, maintenanceId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer maintenanceId){
        return ResponseEntity.ok()
                .body(maintenanceService.deleteMaintenance(maintenanceId));
    }
}
