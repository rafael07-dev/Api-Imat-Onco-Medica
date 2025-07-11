package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.application.usecase.staff.GetAllMaintenanceStaffUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllMaintenanceStaffUseCaseConfig {

    @Bean
    public GetAllMaintenanceStaffUseCase getAllMaintenanceStaffUseCase(MaintenanceStaffRepository maintenanceStaffRepository,
                                                                       MaintenanceStaffMapper maintenanceStaffMapper) {
        return new GetAllMaintenanceStaffUseCase(maintenanceStaffRepository, maintenanceStaffMapper);
    }
}
