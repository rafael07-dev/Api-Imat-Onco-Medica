package com.imat.oncomedica.inventory_management.controller;

import com.imat.oncomedica.inventory_management.Models.Equipment;
import com.imat.oncomedica.inventory_management.dto.EquipmentDTO;
import com.imat.oncomedica.inventory_management.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EquipmentDTO>> getAll(){
        return ResponseEntity.ok().body(equipmentService.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipmentService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment){
        return ResponseEntity.created(URI.create("/api/equipments/" + equipment.getId()))
                .body(equipmentService.saveEquipment(equipment));
    }

    @PutMapping("/update/{idEquipment}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable("idEquipment") Integer id,
                                                        @RequestBody EquipmentDTO equipmentDTO){
        return ResponseEntity.ok().body(equipmentService.updateEquipment(id, equipmentDTO));
    }

    @DeleteMapping("/delete/{idEquipment}")
    public ResponseEntity<EquipmentDTO> deleteEquipment(@PathVariable("idEquipment") Integer id){
        return ResponseEntity.accepted().body(equipmentService.deleteEquipment(id));
    }
}
