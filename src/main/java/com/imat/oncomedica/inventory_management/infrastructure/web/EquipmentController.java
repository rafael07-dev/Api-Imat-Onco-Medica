package com.imat.oncomedica.inventory_management.infrastructure.web;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.application.dto.EquipmentDTO;
import com.imat.oncomedica.inventory_management.application.service.EquipmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final EquipmentServiceImpl equipmentServiceImpl;

    public EquipmentController(EquipmentServiceImpl equipmentServiceImpl) {
        this.equipmentServiceImpl = equipmentServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<EquipmentDTO>> getAll(){
        return ResponseEntity.ok().body(equipmentServiceImpl.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipmentServiceImpl.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment){
        return ResponseEntity.created(URI.create("/api/equipments/" + equipment.getId()))
                .body(equipmentServiceImpl.saveEquipment(equipment));
    }

    @PutMapping("/update/{idEquipment}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable("idEquipment") Integer id,
                                                        @RequestBody EquipmentDTO equipmentDTO){
        return ResponseEntity.ok().body(equipmentServiceImpl.updateEquipment(id, equipmentDTO));
    }

    @DeleteMapping("/delete/{idEquipment}")
    public ResponseEntity<EquipmentDTO> deleteEquipment(@PathVariable("idEquipment") Integer id){
        return ResponseEntity.accepted().body(equipmentServiceImpl.deleteEquipment(id));
    }
}
