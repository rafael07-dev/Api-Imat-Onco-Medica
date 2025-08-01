package com.imat.oncomedica.inventory_management.application.usecase.equipment;

import com.imat.oncomedica.inventory_management.domain.model.Equipment;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadEquipmentImageUseCase {

    private final EquipmentRepository repository;
    private final FileStorageService fileStorageService;

    public UploadEquipmentImageUseCase(EquipmentRepository repository, FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    public String execute(Integer equipmentId, MultipartFile file) {
        Equipment equipment = repository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException(equipmentId));

        String imageUrl = fileStorageService.uploadEquipmentImage(equipmentId, file);

        equipment.setImageUrl(imageUrl);

        repository.save(equipment);

        return imageUrl;
    }
}
