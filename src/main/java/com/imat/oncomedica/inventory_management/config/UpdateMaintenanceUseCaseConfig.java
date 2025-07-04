package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.maintenance.UpdateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.repository.SpringDataMaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMaintenanceUseCaseConfig {

    @Bean
    public UpdateMaintenanceUseCase updateMaintenanceUseCase(
            SpringDataMaintenanceRepository springDataMaintenanceRepository,
            MaintenanceMapper maintenanceMapper,
            MaintenanceStaffRepository maintenanceStaffRepository,
            EquipmentRepository equipmentRepository
    ) {
        return new UpdateMaintenanceUseCase(
                springDataMaintenanceRepository,
                maintenanceMapper,
                maintenanceStaffRepository,
                equipmentRepository
        );
    }
}
