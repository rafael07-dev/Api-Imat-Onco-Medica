package com.imat.oncomedica.inventory_management.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String upload(Integer equipmentId, MultipartFile file);
}
