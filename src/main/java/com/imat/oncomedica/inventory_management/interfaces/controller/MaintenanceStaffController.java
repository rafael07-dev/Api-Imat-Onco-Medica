package com.imat.oncomedica.inventory_management.interfaces.controller;

import com.imat.oncomedica.inventory_management.application.dto.staff.CreateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.dto.staff.MaintenanceStaffResponse;
import com.imat.oncomedica.inventory_management.application.dto.staff.UpdateMaintenanceStaffRequest;
import com.imat.oncomedica.inventory_management.application.usecase.staff.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/maintenance-staff")
public class MaintenanceStaffController {

    private final CreateMaintenanceStaffUseCase createMaintenanceStaffUseCase;
    private final GetAllMaintenanceStaffUseCase getAllMaintenanceStaffUseCase;
    private final GetMaintenanceStaffByIdUseCase getMaintenanceStaffByIdUseCase;
    private final UpdateMaintenanceStaffUseCase updateMaintenanceStaffUseCase;
    private final UploadStaffSignatureUseCase uploadStaffSignatureUseCase;

    public MaintenanceStaffController(CreateMaintenanceStaffUseCase createMaintenanceStaffUseCase,
                                      GetAllMaintenanceStaffUseCase getAllMaintenanceStaffUseCase,
                                      GetMaintenanceStaffByIdUseCase getMaintenanceStaffByIdUseCase,
                                      UpdateMaintenanceStaffUseCase updateMaintenanceStaffUseCase,
                                      UploadStaffSignatureUseCase uploadStaffSignatureUseCase) {
        this.createMaintenanceStaffUseCase = createMaintenanceStaffUseCase;
        this.getAllMaintenanceStaffUseCase = getAllMaintenanceStaffUseCase;
        this.getMaintenanceStaffByIdUseCase = getMaintenanceStaffByIdUseCase;
        this.updateMaintenanceStaffUseCase = updateMaintenanceStaffUseCase;
        this.uploadStaffSignatureUseCase = uploadStaffSignatureUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceStaffResponse> save(@RequestBody CreateMaintenanceStaffRequest request){
        MaintenanceStaffResponse maintenanceStaffSaved = createMaintenanceStaffUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/maintenance-staff/" + maintenanceStaffSaved.getId()))
                .body(maintenanceStaffSaved);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<MaintenanceStaffResponse> updateStaff(@RequestBody UpdateMaintenanceStaffRequest request, @PathVariable Integer id){
        return ResponseEntity.ok().body(updateMaintenanceStaffUseCase.execute(request, id));
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceStaffResponse>> findAll(){
        return ResponseEntity.ok().body(getAllMaintenanceStaffUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceStaffResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok().body(getMaintenanceStaffByIdUseCase.execute(id));
    }

    @PostMapping(value = "/upload-signature/{id}", consumes = "multipart/form-data")
    public String uploadSignature(@RequestParam("file") MultipartFile file, @PathVariable Integer id){
        return uploadStaffSignatureUseCase.execute(file, id);
    }
}
