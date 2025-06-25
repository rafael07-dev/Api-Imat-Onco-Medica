package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceNotFoundException;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadMaintenanceImageUseCase {

    private final FileStorageService fileStorageService;
    private final MaintenanceRepository maintenanceRepository;

    public UploadMaintenanceImageUseCase(FileStorageService fileStorageService, MaintenanceRepository maintenanceRepository) {
        this.fileStorageService = fileStorageService;
        this.maintenanceRepository = maintenanceRepository;
    }

    public String UploadMaintenanceImageUseCase(MultipartFile file, Integer maintenanceId) {
        var url = fileStorageService.uploadMaintenanceImage(maintenanceId, file);

        var maintenanceSaved = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(maintenanceId));

        maintenanceSaved.setEvidenceImg(url);

        maintenanceRepository.save(maintenanceSaved);

        return url;
    }
}
