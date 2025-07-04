package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.GetMaintenanceScheduleByIdUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMaintenanceScheduleByIdUseCaseConfig {

    @Bean
    public GetMaintenanceScheduleByIdUseCase getMaintenanceScheduleByIdUseCase(
            MaintenanceScheduleRepository maintenanceScheduleRepository,
            MaintenanceScheduleMapper maintenanceScheduleMapper
    ) {
        return new GetMaintenanceScheduleByIdUseCase(
            maintenanceScheduleRepository,
                maintenanceScheduleMapper
        );
    }
}
