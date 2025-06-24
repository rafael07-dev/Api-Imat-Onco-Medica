package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.UpdateMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;
import com.imat.oncomedica.inventory_management.infrastructure.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMaintenanceUseCaseConfig {

    @Bean
    public UpdateMaintenanceUseCase updateMaintenanceUseCase(
            MaintenanceRepository maintenanceRepository,
            MaintenanceMapper maintenanceMapper,
            MaintenanceStaffRepository maintenanceStaffRepository,
            EquipmentRepository equipmentRepository,
            OrderRepository orderRepository,
            OrderPdfGenerator orderPdfGenerator
    ) {
        return new UpdateMaintenanceUseCase(
                maintenanceRepository,
                maintenanceMapper,
                maintenanceStaffRepository,
                equipmentRepository,
                orderRepository,
                orderPdfGenerator
        );
    }
}
