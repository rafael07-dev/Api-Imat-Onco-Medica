package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.UpdateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class MaintenanceScheduleController {

    private final CreateMaintenanceScheduleUseCase createMaintenanceScheduleUseCase;
    private final UpdateMaintenanceScheduleUseCase updateMaintenanceScheduleUseCase;
    private final GetAllMaintenanceScheduleUseCase getAllMaintenanceScheduleUseCase;
    private final GetMaintenanceScheduleByIdUseCase getMaintenanceScheduleByIdUseCase;
    private final GetMaintenanceScheduleByYearUseCase getMaintenanceScheduleByYearUseCase;
    private final DeleteMaintenanceScheduleUseCase deleteMaintenanceScheduleUseCase;

    public MaintenanceScheduleController(CreateMaintenanceScheduleUseCase createMaintenanceScheduleUseCase,
                                         UpdateMaintenanceScheduleUseCase updateMaintenanceScheduleUseCase,
                                         GetAllMaintenanceScheduleUseCase getAllMaintenanceScheduleUseCase,
                                         GetMaintenanceScheduleByIdUseCase getMaintenanceScheduleByIdUseCase,
                                         GetMaintenanceScheduleByYearUseCase getMaintenanceScheduleByYearUseCase,
                                         DeleteMaintenanceScheduleUseCase deleteMaintenanceScheduleUseCase) {
        this.createMaintenanceScheduleUseCase = createMaintenanceScheduleUseCase;
        this.updateMaintenanceScheduleUseCase = updateMaintenanceScheduleUseCase;
        this.getAllMaintenanceScheduleUseCase = getAllMaintenanceScheduleUseCase;
        this.getMaintenanceScheduleByIdUseCase = getMaintenanceScheduleByIdUseCase;
        this.getMaintenanceScheduleByYearUseCase = getMaintenanceScheduleByYearUseCase;
        this.deleteMaintenanceScheduleUseCase = deleteMaintenanceScheduleUseCase;
    }


    @GetMapping("/")
    public ResponseEntity<List<MaintenanceScheduleResponse>> getAllMaintenanceSchedules() {
        return ResponseEntity.ok().body(getAllMaintenanceScheduleUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceScheduleResponse> getMaintenanceScheduleById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(getMaintenanceScheduleByIdUseCase.execute(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MaintenanceScheduleResponse>> getMaintenanceScheduleByYear(@RequestParam(required = false) Integer year) {
        return ResponseEntity.ok().body(getMaintenanceScheduleByYearUseCase.execute(year));
    }

    @PostMapping("/create")
    public ResponseEntity<MaintenanceScheduleResponse> save(@RequestBody CreateMaintenanceScheduleRequest request) {
        var maintenanceCreated = createMaintenanceScheduleUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/schedule/" + maintenanceCreated.getId()))
                .body(maintenanceCreated);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MaintenanceScheduleResponse> update(@RequestBody UpdateMaintenanceScheduleRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(updateMaintenanceScheduleUseCase.execute(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(deleteMaintenanceScheduleUseCase.execute(id));
    }
}
