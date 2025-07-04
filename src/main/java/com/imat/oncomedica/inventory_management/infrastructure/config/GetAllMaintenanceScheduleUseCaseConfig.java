package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.GetAllMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllMaintenanceScheduleUseCaseConfig {

    @Bean
    public GetAllMaintenanceScheduleUseCase getAllMaintenanceScheduleUseCase(
            MaintenanceScheduleRepository maintenanceScheduleRepository,
            MaintenanceScheduleMapper maintenanceScheduleMapper
    ) {
        return new GetAllMaintenanceScheduleUseCase(
            maintenanceScheduleRepository, maintenanceScheduleMapper
        );
    }
}
