package com.imat.oncomedica.inventory_management.application.usecase;

import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadStaffSignatureUseCase {

    private final FileStorageService fileStorageService;
    private final MaintenanceStaffRepository managementStaffRepository;

    public UploadStaffSignatureUseCase(FileStorageService fileStorageService, MaintenanceStaffRepository managementStaffRepository) {
        this.fileStorageService = fileStorageService;
        this.managementStaffRepository = managementStaffRepository;
    }

    public String execute(MultipartFile file, Integer id){
        var staff = managementStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        var pathUrl = fileStorageService.uploadStaffSignature(id, file);

        staff.setSignaturePath(pathUrl);

        managementStaffRepository.save(staff);
        return pathUrl;
    }
}
