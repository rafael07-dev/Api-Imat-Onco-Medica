package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.builder.UpdateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMaintenanceScheduleBuilderConfig {

    @Bean
    public UpdateMaintenanceScheduleBuilder updateMaintenanceScheduleBuilder(
            MaintenanceScheduleRepository maintenanceScheduleRepository
    ) {
        return new UpdateMaintenanceScheduleBuilder(
                maintenanceScheduleRepository
        );
    }
}
