package com.imat.oncomedica.inventory_management.infrastructure.storage;

import com.imat.oncomedica.inventory_management.domain.exception.IllegalFileExtension;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageServiceImpl.class);
    private final Path fileStorageLocation;
    private static final String IMG_EQUIPMENT_LOCATION = "/equipment/";
    private static final String IMG_MAINTENANCE_LOCATION = "/maintenance/";
    private static final String SIGNATURES_STAFF = "/signatures/staff/";
    private static final String SIGNATURES_ADMIN = "/signatures/admin/";
    private static final List<String> ALLOWED_EXTENSIONS = List.of(".jpg", ".jpeg", ".png", ".webp");

    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get("uploads/images").toAbsolutePath().normalize();
        initStorageDirectory();
        initStorageEquipment();
        initStorageMaintenance();
    }

    @Override
    public String uploadEquipmentImage(Integer equipmentId, MultipartFile file) {
        return storeFile(file, IMG_EQUIPMENT_LOCATION, equipmentId);
    }

    @Override
    public String uploadMaintenanceImage(Integer maintenanceId, MultipartFile file) {
        return storeFile(file, IMG_MAINTENANCE_LOCATION, maintenanceId);
    }

    @Override
    public String uploadStaffSignature(Integer staffId, MultipartFile file) {
        return storeFile(file, SIGNATURES_STAFF, staffId);
    }

    @Override
    public String uploadAdminSignature(Integer adminId, MultipartFile file) {
        return storeFile(file, SIGNATURES_ADMIN, adminId);
    }

    private void initStorageDirectory() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not create directory {}", e);
        }
    }

    private void initStorageEquipment(){
        try {
            Files.createDirectories(this.fileStorageLocation.resolve(IMG_EQUIPMENT_LOCATION));
        }catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not create directory {}", e);
        }
    }

    private void initStorageMaintenance(){
        try {
            Files.createDirectories(this.fileStorageLocation.resolve(IMG_MAINTENANCE_LOCATION));
        }catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not create directory {}", e);
        }
    }

    private String storeFile(MultipartFile file, String dir, Integer id) {
        validateExtension(file);

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID() + extension;

        Path dirPath = this.fileStorageLocation.resolve(dir + id + "/");
        Path targetPath = dirPath.resolve(newFileName);

        try {
            Files.createDirectories(dirPath);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            log.error("Could not copy file {}", e.getMessage());
            throw new RuntimeException("Could not copy file {}", e);
        }

        return targetPath.toString();
    }

    private void validateExtension(MultipartFile file) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalFileExtension("Invalid file format: only JPG, JPEG, PNG and WEBP are allowed.");
        }
    }
}
