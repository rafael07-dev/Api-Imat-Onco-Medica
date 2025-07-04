package com.imat.oncomedica.inventory_management.interfaces.controller;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.usecase.UploadStaffSignatureUseCase;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceStaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService maintenanceStaffService;
    private final UploadStaffSignatureUseCase uploadStaffSignatureUseCase;

    public MaintenanceStaffController(MaintenanceStaffService maintenanceStaffService, UploadStaffSignatureUseCase uploadStaffSignatureUseCase) {
        this.maintenanceStaffService = maintenanceStaffService;
        this.uploadStaffSignatureUseCase = uploadStaffSignatureUseCase;
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

    @PostMapping(value = "/upload-signature/{id}", consumes = "multipart/form-data")
    public String uploadSignature(@RequestParam("file") MultipartFile file, @PathVariable Integer id){
        return uploadStaffSignatureUseCase.execute(file, id);
    }
}
