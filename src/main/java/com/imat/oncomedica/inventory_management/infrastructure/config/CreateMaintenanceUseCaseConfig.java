package com.imat.oncomedica.inventory_management.infrastructure.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.maintenance.CreateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.application.usecase.order.CreateOrderUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.domain.service.NotificationService;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
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
