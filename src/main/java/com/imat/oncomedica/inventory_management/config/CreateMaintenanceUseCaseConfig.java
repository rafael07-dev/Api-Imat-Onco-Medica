package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.CreateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.CreateOrderUseCase;
import com.imat.oncomedica.inventory_management.domain.service.NotificationService;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMaintenanceUseCaseConfig {

    @Bean
    public CreateMaintenanceUseCase createMaintenanceUseCase(
            MaintenanceRepository maintenanceRepository,
            EquipmentRepository equipmentRepository,
            MaintenanceStaffRepository maintenanceStaffRepository,
            MaintenanceMapper maintenanceMapper,
            NotificationService notificationService,
            CreateOrderUseCase createOrderUseCase) {
        return new CreateMaintenanceUseCase(
                maintenanceRepository,
                equipmentRepository,
                maintenanceStaffRepository,
                maintenanceMapper,
                notificationService,
                createOrderUseCase);
    }
}
