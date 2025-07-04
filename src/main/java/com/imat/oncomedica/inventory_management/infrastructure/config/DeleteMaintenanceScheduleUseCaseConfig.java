package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.usecase.schedule.DeleteMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteMaintenanceScheduleUseCaseConfig {

    @Bean
    public DeleteMaintenanceScheduleUseCase deleteMaintenanceScheduleUseCase(
            MaintenanceScheduleRepository maintenanceScheduleRepository
    ) {
        return new DeleteMaintenanceScheduleUseCase(maintenanceScheduleRepository);
    }
}
