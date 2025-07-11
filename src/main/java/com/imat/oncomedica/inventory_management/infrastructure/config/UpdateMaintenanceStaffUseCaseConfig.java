package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.application.usecase.staff.UpdateMaintenanceStaffUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMaintenanceStaffUseCaseConfig {

    @Bean
    public UpdateMaintenanceStaffUseCase updateMaintenanceStaffUseCase(MaintenanceStaffRepository repository,
                                                                       MaintenanceStaffMapper mapper) {
        return new UpdateMaintenanceStaffUseCase(repository, mapper);
    }
}
