package com.imat.oncomedica.inventory_management.application.usecase.staff;

import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceStaffNotFound;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadStaffSignatureUseCase {

    private final FileStorageService fileStorageService;
    private final MaintenanceStaffRepository maintenanceStaffRepository;

    public UploadStaffSignatureUseCase(FileStorageService fileStorageService, MaintenanceStaffRepository maintenanceStaffRepository) {
        this.fileStorageService = fileStorageService;
        this.maintenanceStaffRepository = maintenanceStaffRepository;
    }


    public String execute(MultipartFile file, Integer id){
        var staff = maintenanceStaffRepository.findById(id)
                .orElseThrow(() -> new MaintenanceStaffNotFound(id));

        var pathUrl = fileStorageService.uploadStaffSignature(id, file);

        staff.setSignaturePath(pathUrl);

        maintenanceStaffRepository.save(staff);
        return pathUrl;
    }
}
