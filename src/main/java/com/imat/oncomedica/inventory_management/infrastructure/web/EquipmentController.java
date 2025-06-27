package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.equipment.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.dto.equipment.UpdateEquipmentRequest;
import com.imat.oncomedica.inventory_management.domain.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.imat.oncomedica.inventory_management.application.usecase.UploadEquipmentImageUseCase;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final UploadEquipmentImageUseCase uploadEquipmentImageUseCase;

    public EquipmentController(EquipmentService equipmentService, UploadEquipmentImageUseCase uploadEquipmentImageUseCase) {
        this.equipmentService = equipmentService;
        this.uploadEquipmentImageUseCase = uploadEquipmentImageUseCase;
    }


    @GetMapping("/")
    public ResponseEntity<List<EquipmentResponse>> getAll(){
        return ResponseEntity.ok().body(equipmentService.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody CreateEquipmentRequest equipment){
        EquipmentResponse equipmentSaved = equipmentService.saveEquipment(equipment);
        return ResponseEntity.created(URI.create("/api/equipments/" + equipmentSaved.getId()))
                .body(equipmentSaved);
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public String uploadImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
        return uploadEquipmentImageUseCase.execute(id, file);
    }

    @PutMapping("/update/{idEquipment}")
    public ResponseEntity<EquipmentResponse> updateEquipment(@PathVariable("idEquipment") Integer id,
                                                        @RequestBody UpdateEquipmentRequest equipment){
        return ResponseEntity.ok().body(equipmentService.updateEquipment(equipment, id));
    }

    @DeleteMapping("/delete/{idEquipment}")
    public ResponseEntity<EquipmentResponse> deleteEquipment(@PathVariable("idEquipment") Integer id){
        return ResponseEntity.accepted().body(equipmentService.deleteEquipment(id));
    }
}
