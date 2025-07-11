package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.application.usecase.staff.CreateMaintenanceStaffUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMaintenanceStaffUseCaseConfig {
    @Bean
    public CreateMaintenanceStaffUseCase createMaintenanceStaffUseCase(MaintenanceStaffRepository maintenanceStaffRepository,
                                                                       MaintenanceStaffMapper maintenanceStaffMapper) {
        return new CreateMaintenanceStaffUseCase(maintenanceStaffRepository, maintenanceStaffMapper);
    }

}
