package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.builder.CreateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.factory.MaintenanceScheduleFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMaintenanceScheduleBuilderConfig {

    @Bean
    public CreateMaintenanceScheduleBuilder createMaintenanceScheduleBuilderConfig(
            MaintenanceScheduleFactory maintenanceScheduleFactory
    ) {
        return new CreateMaintenanceScheduleBuilder(
                maintenanceScheduleFactory
        );
    }
}
