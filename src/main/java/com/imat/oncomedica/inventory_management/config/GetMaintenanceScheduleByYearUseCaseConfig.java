package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.schedule.GetMaintenanceScheduleByYearUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMaintenanceScheduleByYearUseCaseConfig {

    @Bean
    public GetMaintenanceScheduleByYearUseCase getMaintenanceScheduleByYearUseCase(
            MaintenanceScheduleRepository maintenanceScheduleRepository,
            MaintenanceScheduleMapper maintenanceScheduleMapper
    ) {
        return new GetMaintenanceScheduleByYearUseCase(
                maintenanceScheduleRepository,
                maintenanceScheduleMapper
        );
    }
}
