package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.CompleteMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;
import com.imat.oncomedica.inventory_management.domain.repository.EquipmentRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceStaffRepository;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompleteMaintenanceUseCaseConfig {

    @Bean
    public CompleteMaintenanceUseCase completeMaintenanceUseCase (
            MaintenanceStaffRepository maintenanceStaffRepository,
            MaintenanceRepository maintenanceRepository,
            MaintenanceMapper maintenanceMapper,
            EquipmentRepository equipmentRepository,
            OrderPdfGenerator orderPdfGenerator,
            OrderRepository orderRepository
    ) {
        return new CompleteMaintenanceUseCase(
                maintenanceStaffRepository,
                maintenanceRepository,
                maintenanceMapper,
                equipmentRepository,
                orderPdfGenerator,
                orderRepository);
    }
}
