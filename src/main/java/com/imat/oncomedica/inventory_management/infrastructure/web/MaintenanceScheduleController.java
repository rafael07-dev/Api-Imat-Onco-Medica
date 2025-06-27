package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.service.MaintenanceScheduleServiceImpl;
import com.imat.oncomedica.inventory_management.application.usecase.CreateMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.service.MaintenanceScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class MaintenanceScheduleController {

    private final MaintenanceScheduleService maintenanceScheduleService;
    private final CreateMaintenanceScheduleUseCase createMaintenanceScheduleUseCase;

    public MaintenanceScheduleController(MaintenanceScheduleServiceImpl maintenanceScheduleServiceImpl, CreateMaintenanceScheduleUseCase createMaintenanceScheduleUseCase) {
        this.maintenanceScheduleService = maintenanceScheduleServiceImpl;
        this.createMaintenanceScheduleUseCase = createMaintenanceScheduleUseCase;
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceScheduleResponse>> getAllMaintenanceSchedules() {
        return ResponseEntity.ok().body(maintenanceScheduleService.getAllMaintenanceSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceScheduleResponse> getMaintenanceScheduleById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(maintenanceScheduleService.getMaintenanceScheduleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceScheduleResponse> save(@RequestBody CreateMaintenanceScheduleRequest request) {
        var maintenanceCreated = createMaintenanceScheduleUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/schedule/" + maintenanceCreated.getId()))
                .body(maintenanceCreated);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MaintenanceScheduleResponse> update(@RequestBody UpdateMaintenanceScheduleRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(maintenanceScheduleService.updateMaintenanceSchedule (request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(maintenanceScheduleService.deleteMaintenanceSchedule(id));
    }
}
