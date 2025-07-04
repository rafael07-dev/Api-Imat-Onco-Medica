package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.builder.CreateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.CreateMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMaintenanceScheduleUseCaseConfig {

    @Bean
    public CreateMaintenanceScheduleUseCase createMaintenanceScheduleUseCase(
            MaintenanceScheduleRepository maintenanceScheduleRepository,
            MaintenanceScheduleMapper maintenanceScheduleMapper,
            CreateMaintenanceScheduleBuilder createMaintenanceScheduleBuilder
    ) {
        return new CreateMaintenanceScheduleUseCase(
                maintenanceScheduleRepository,
                maintenanceScheduleMapper,
                createMaintenanceScheduleBuilder
        );
    }
}
