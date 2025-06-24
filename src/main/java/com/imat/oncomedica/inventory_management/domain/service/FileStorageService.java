package com.imat.oncomedica.inventory_management.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String uploadEquipmentImage(Integer equipmentId, MultipartFile file);
    String uploadMaintenanceImage(Integer maintenanceId, MultipartFile file);
    String uploadStaffSignature(Integer staffId, MultipartFile file);
    String uploadAdminSignature(Integer adminId, MultipartFile file);
}
