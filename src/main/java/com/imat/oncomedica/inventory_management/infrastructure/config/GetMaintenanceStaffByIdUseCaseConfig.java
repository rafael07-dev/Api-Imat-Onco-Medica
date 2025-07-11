package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceStaffMapper;
import com.imat.oncomedica.inventory_management.application.usecase.staff.GetMaintenanceStaffByIdUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMaintenanceStaffByIdUseCaseConfig {

    @Bean
    public GetMaintenanceStaffByIdUseCase getMaintenanceStaffByIdUseCase(MaintenanceStaffRepository repository, MaintenanceStaffMapper mapper) {
        return new GetMaintenanceStaffByIdUseCase(repository, mapper);
    }
}
