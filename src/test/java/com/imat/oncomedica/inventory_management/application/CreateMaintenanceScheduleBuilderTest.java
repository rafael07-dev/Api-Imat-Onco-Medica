package com.imat.oncomedica.inventory_management.application;

import com.imat.oncomedica.inventory_management.application.builder.CreateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMonthlyMaintenanceTypeRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.factory.MaintenanceScheduleFactory;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.exception.MonthlyMaintenanceNullOrEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateMaintenanceScheduleBuilderTest {

    @Mock
    private MaintenanceScheduleFactory factory;


    private CreateMaintenanceScheduleBuilder builder;

    @BeforeEach
    void setUp(){
        builder = new CreateMaintenanceScheduleBuilder(factory);
    }

    @Test
    void shouldCreateMaintenanceScheduleSuccessfully(){
        var monthlyMaintenanceType = new CreateMonthlyMaintenanceTypeRequest();
        monthlyMaintenanceType.setQuantity(2);
        monthlyMaintenanceType.setMaintenanceType(MaintenanceTypeEnum.MP.toString());

        var monthlyMaintenance = new CreateMonthlyMaintenanceRequest();
        monthlyMaintenance.setMaintenanceType(List.of(monthlyMaintenanceType));

        MaintenanceScheduleRequest request = new MaintenanceScheduleRequest();
        request.setMonthlyMaintenances(List.of(monthlyMaintenance));

        MaintenanceSchedule schedule = new MaintenanceSchedule();

        when(factory.buildMaintenanceSchedule(request)).thenReturn(schedule);

        var result = builder.build(request);

        assertEquals(schedule, result);

        verify(factory).buildMaintenanceSchedule(request);
    }

    @Test
    void shouldThrowExceptionWhenMonthlyMaintenancesIsEmpty(){

        var request = new MaintenanceScheduleRequest();
        request.setMonthlyMaintenances(Collections.emptyList());

        var result = assertThrows(MonthlyMaintenanceNullOrEmptyException.class,
                () -> builder.build(request));

        assertEquals("should have at least one maintenance", result.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMonthlyMaintenancesIsNull(){

        var request = new MaintenanceScheduleRequest();
        request.setMonthlyMaintenances(null);

        var result = assertThrows(MonthlyMaintenanceNullOrEmptyException.class,
                () -> builder.build(request));

        assertEquals("should have at least one maintenance", result.getMessage());

    }

}
