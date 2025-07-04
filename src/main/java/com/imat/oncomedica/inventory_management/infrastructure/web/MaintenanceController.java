package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.UpdateMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.usecase.maintenance.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final CreateMaintenanceUseCase createMaintenanceUseCase;
    private final UploadMaintenanceImageUseCase uploadMaintenanceImageUseCase;
    private final UpdateMaintenanceUseCase updateMaintenanceUseCase;
    private final CompleteMaintenanceUseCase completeMaintenanceUseCase;
    private final DeleteMaintenanceUseCase deleteMaintenanceUseCase;
    private final GetAllMaintenanceUseCase getAllMaintenanceUseCase;
    private final GetMaintenanceByEquipmentIdUseCase getMaintenanceByEquipmentIdUseCase;
    private final GetMaintenancesByMaintenanceStaffIdUseCase getMaintenancesByMaintenanceStaffIdUseCase;

    public MaintenanceController(CreateMaintenanceUseCase createMaintenanceUseCase, UploadMaintenanceImageUseCase uploadMaintenanceImageUseCase, UpdateMaintenanceUseCase updateMaintenanceUseCase, CompleteMaintenanceUseCase completeMaintenanceUseCase, DeleteMaintenanceUseCase deleteMaintenanceUseCase, GetAllMaintenanceUseCase getAllMaintenanceUseCase, GetMaintenanceByEquipmentIdUseCase getMaintenanceByEquipmentIdUseCase, GetMaintenancesByMaintenanceStaffIdUseCase getMaintenancesByMaintenanceStaffIdUseCase) {
        this.createMaintenanceUseCase = createMaintenanceUseCase;
        this.uploadMaintenanceImageUseCase = uploadMaintenanceImageUseCase;
        this.updateMaintenanceUseCase = updateMaintenanceUseCase;
        this.completeMaintenanceUseCase = completeMaintenanceUseCase;
        this.deleteMaintenanceUseCase = deleteMaintenanceUseCase;
        this.getAllMaintenanceUseCase = getAllMaintenanceUseCase;
        this.getMaintenanceByEquipmentIdUseCase = getMaintenanceByEquipmentIdUseCase;
        this.getMaintenancesByMaintenanceStaffIdUseCase = getMaintenancesByMaintenanceStaffIdUseCase;
    }


    @GetMapping("/")
    public ResponseEntity<List<MaintenanceResponse>> findAll(){
        return ResponseEntity.ok().body(getAllMaintenanceUseCase.execute());
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<MaintenanceResponse>> getMaintenancesByEquipmentId(@PathVariable Integer equipmentId) {
        return ResponseEntity.ok().body(getMaintenanceByEquipmentIdUseCase.execute(equipmentId))
    }

    @GetMapping("/{maintenanceStaffId}")
    public ResponseEntity<List<MaintenanceResponse>> getMaintenancesByMaintenanceStaffId(@PathVariable Integer maintenanceStaffId) {
        List<MaintenanceResponse> maintenances = getMaintenancesByMaintenanceStaffIdUseCase.execute(maintenanceStaffId);
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
                .body(deleteMaintenanceUseCase.execute(maintenanceId));
    }
}
