package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.builder.UpdateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.UpdateMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMaintenanceScheduleUseCaseConfig {

    @Bean
    public UpdateMaintenanceScheduleUseCase updateMaintenanceScheduleUseCase(
            UpdateMaintenanceScheduleBuilder updateMaintenanceScheduleBuilder,
            MaintenanceScheduleRepository maintenanceScheduleRepository,
            MaintenanceScheduleMapper maintenanceScheduleMapper
    ) {
        return new UpdateMaintenanceScheduleUseCase(
                updateMaintenanceScheduleBuilder,
                maintenanceScheduleRepository,
                maintenanceScheduleMapper
        );
    }
}
