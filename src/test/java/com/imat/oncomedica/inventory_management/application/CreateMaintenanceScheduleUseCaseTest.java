package com.imat.oncomedica.inventory_management.application;

import com.imat.oncomedica.inventory_management.application.builder.CreateMaintenanceScheduleBuilder;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.CreateMonthlyMaintenanceTypeRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMaintenanceScheduleRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.CreateMonthlyMaintenanceRequest;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.usecase.CreateMaintenanceScheduleUseCase;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleAlreadyExistsException;
import com.imat.oncomedica.inventory_management.infrastructure.repository.MaintenanceScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateMaintenanceScheduleUseCaseTest {

    @Mock
    private MaintenanceScheduleRepository repository;

    @Mock
    private MaintenanceScheduleMapper mapper;

    @Mock
    private CreateMaintenanceScheduleBuilder builder;

    private CreateMaintenanceScheduleUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateMaintenanceScheduleUseCase(repository, mapper, builder);
    }

    @Test
    void shouldCreateAnMaintenanceScheduleSuccessfully(){

        var monthlyMaintenanceType = new CreateMonthlyMaintenanceTypeRequest();
        monthlyMaintenanceType.setMaintenanceType(MaintenanceTypeEnum.MP.toString());
        monthlyMaintenanceType.setQuantity(5);

        var monthlyMaintenanceRequest = new CreateMonthlyMaintenanceRequest();
        monthlyMaintenanceRequest.setYear(2024);
        monthlyMaintenanceRequest.setMonth(1);
        monthlyMaintenanceRequest.setMaintenanceType(List.of(monthlyMaintenanceType));

        var request = new CreateMaintenanceScheduleRequest();
        request.setMonthlyMaintenances(List.of(monthlyMaintenanceRequest));

        var response = new MaintenanceScheduleResponse();
        var schedule = new MaintenanceSchedule();

        when(builder.build(request)).thenReturn(schedule);
        when(repository.save(schedule)).thenReturn(schedule);
        when(mapper.buildMaintenanceScheduleResponse(schedule)).thenReturn(response);

        var result = useCase.execute(request);

        assertEquals(result, response);

        verify(builder).build(request);
        verify(repository).save(schedule);
        verify(mapper).buildMaintenanceScheduleResponse(schedule);
    }

    @Test
    void shouldThrowAnExceptionIfMaintenanceScheduleExistForRequestedEquipmentId(){
        Integer id = 1;

        CreateMaintenanceScheduleRequest request = new CreateMaintenanceScheduleRequest();
        request.setEquipmentId(id);

        when(repository.findMaintenanceScheduleByEquipment_Id(id)).thenReturn(Optional.of(new MaintenanceSchedule()));

        assertThrows(MaintenanceScheduleAlreadyExistsException.class,
                () -> useCase.execute(request));

        verify(repository).findMaintenanceScheduleByEquipment_Id(id);
        verify(repository, never()).save(any());
        verify(mapper, never()).buildMaintenanceScheduleResponse(any());
    }
}
