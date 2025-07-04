package com.imat.oncomedica.inventory_management.config;

import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceMapper;
import com.imat.oncomedica.inventory_management.application.usecase.maintenance.CompleteMaintenanceUseCase;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceRepository;
import com.imat.oncomedica.inventory_management.infrastructure.report.OrderPdfGenerator;
import com.imat.oncomedica.inventory_management.infrastructure.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompleteMaintenanceUseCaseConfig {

    @Bean
    public CompleteMaintenanceUseCase completeMaintenanceUseCase (
            MaintenanceRepository maintenanceRepository,
            MaintenanceMapper maintenanceMapper,
            OrderPdfGenerator orderPdfGenerator,
            OrderRepository orderRepository
    ) {
        return new CompleteMaintenanceUseCase(
                maintenanceRepository,
                maintenanceMapper,
                orderPdfGenerator,
                orderRepository);
    }
}
