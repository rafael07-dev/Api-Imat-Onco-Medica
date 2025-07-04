package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.application.dto.equipment.CreateEquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentRequest;
import com.imat.oncomedica.inventory_management.application.dto.equipment.EquipmentResponse;
import com.imat.oncomedica.inventory_management.application.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final CreateEquipmentUseCase createEquipmentUseCase;
    private final GetAllEquipmentUseCase getAllEquipmentUseCase;
    private final DeleteEquipmentUseCase deleteEquipmentUseCase;
    private final GetEquipmentByIdUseCase getEquipmentByIdUseCase;
    private final UpdateEquipmentUseCase updateEquipmentUseCase;
    private final UploadEquipmentImageUseCase uploadEquipmentImageUseCase;

    public EquipmentController(CreateEquipmentUseCase createEquipmentUseCase, GetAllEquipmentUseCase getAllEquipmentUseCase, DeleteEquipmentUseCase deleteEquipmentUseCase, GetEquipmentByIdUseCase getEquipmentByIdUseCase, UpdateEquipmentUseCase updateEquipmentUseCase, UploadEquipmentImageUseCase uploadEquipmentImageUseCase) {
        this.createEquipmentUseCase = createEquipmentUseCase;
        this.getAllEquipmentUseCase = getAllEquipmentUseCase;
        this.deleteEquipmentUseCase = deleteEquipmentUseCase;
        this.getEquipmentByIdUseCase = getEquipmentByIdUseCase;
        this.updateEquipmentUseCase = updateEquipmentUseCase;
        this.uploadEquipmentImageUseCase = uploadEquipmentImageUseCase;
    }


    @GetMapping("/")
    public ResponseEntity<List<EquipmentResponse>> getAll(){
        return ResponseEntity.ok().body(getAllEquipmentUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok().body(getEquipmentByIdUseCase.execute(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody CreateEquipmentRequest equipment){
        EquipmentResponse equipmentSaved = createEquipmentUseCase.execute(equipment);
        return ResponseEntity.created(URI.create("/api/equipments/" + equipmentSaved.getId()))
                .body(equipmentSaved);
    }

    @PostMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public String uploadImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
        return uploadEquipmentImageUseCase.execute(id, file);
    }

    @PutMapping("/update/{idEquipment}")
    public ResponseEntity<EquipmentResponse> updateEquipment(@PathVariable("idEquipment") Integer id,
                                                        @RequestBody EquipmentRequest equipment){
        return ResponseEntity.ok().body(updateEquipmentUseCase.execute (equipment, id));
    }

    @DeleteMapping("/delete/{idEquipment}")
    public ResponseEntity<EquipmentResponse> deleteEquipment(@PathVariable("idEquipment") Integer id){
        return ResponseEntity.accepted().body(deleteEquipmentUseCase.execute(id));
    }
}
