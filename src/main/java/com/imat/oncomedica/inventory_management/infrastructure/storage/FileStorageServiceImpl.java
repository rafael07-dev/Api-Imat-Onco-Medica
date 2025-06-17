package com.imat.oncomedica.inventory_management.infrastructure.storage;

import com.imat.oncomedica.inventory_management.domain.exception.IllegalFileExtension;
import com.imat.oncomedica.inventory_management.domain.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageServiceImpl.class);
    private final Path fileStorageLocation;

    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get("uploads/images").toAbsolutePath().normalize();
        initStorageDirectory();
    }

    @Override
    public String upload(Integer equipmentId, MultipartFile file) {

        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));

        if (!(extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".webp"))) {
            throw new IllegalFileExtension("Invalid file format: only JPG and PNG are allowed.");
        }

        String newFileName = UUID.randomUUID() + extension;
        Path storageLocation = this.fileStorageLocation.resolve(newFileName);

        try{
            Files.copy(file.getInputStream(), storageLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            log.error("Could not copy file {}", e.getMessage());
            throw new RuntimeException("Could not copy file {}", e);
        }

        return "uploads/images/" + newFileName;
    }

    private void initStorageDirectory() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Could not create directory {}", e);
        }
    }
}
