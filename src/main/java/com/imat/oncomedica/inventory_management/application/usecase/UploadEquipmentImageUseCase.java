package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.exception.EquipmentNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
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
