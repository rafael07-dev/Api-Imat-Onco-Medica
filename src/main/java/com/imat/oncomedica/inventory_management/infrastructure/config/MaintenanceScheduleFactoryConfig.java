package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.factory.MaintenanceScheduleFactory;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaintenanceScheduleFactoryConfig {

    @Bean
    public MaintenanceScheduleFactory maintenanceScheduleFactory(
            EquipmentRepository equipmentRepository,
            MaintenanceStaffRepository maintenanceStaffRepository
    ) {
        return new MaintenanceScheduleFactory(
                equipmentRepository,
                maintenanceStaffRepository
        );
    }
}
