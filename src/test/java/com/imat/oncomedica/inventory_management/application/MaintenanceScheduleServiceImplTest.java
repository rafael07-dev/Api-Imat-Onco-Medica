package com.imat.oncomedica.inventory_management.application;

import com.imat.oncomedica.inventory_management.application.dto.maintenance.MonthlyMaintenanceResponse;
import com.imat.oncomedica.inventory_management.application.dto.maintenance.MonthlyMaintenanceTypeResponse;
import com.imat.oncomedica.inventory_management.application.dto.schedule.MaintenanceScheduleResponse;
import com.imat.oncomedica.inventory_management.application.mapper.MaintenanceScheduleMapper;
import com.imat.oncomedica.inventory_management.application.service.MaintenanceScheduleServiceImpl;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.model.MaintenanceTypeEnum;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenance;
import com.imat.oncomedica.inventory_management.domain.model.MonthlyMaintenanceType;
import com.imat.oncomedica.inventory_management.domain.exception.MaintenanceScheduleNotFoundException;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.repository.SpringDataMaintenanceScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaintenanceScheduleServiceImplTest {

    @Mock
    private SpringDataMaintenanceScheduleRepository repository;

    @Mock
    private MaintenanceScheduleMapper mapper;

    @InjectMocks
    private MaintenanceScheduleServiceImpl service;

    @Test
    void shouldReturnMaintenanceScheduleById() {
        Integer id = 1;
        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
        MaintenanceScheduleResponse maintenanceScheduleResponse = new MaintenanceScheduleResponse();

        when(repository.findById(id)).thenReturn(Optional.of(maintenanceSchedule));
        when(mapper.buildMaintenanceScheduleResponse(maintenanceSchedule)).thenReturn(maintenanceScheduleResponse);

        var resp = service.getMaintenanceScheduleById(id);

        assertEquals(maintenanceScheduleResponse, resp);
        verify(repository).findById(id);
        verify(mapper).buildMaintenanceScheduleResponse(maintenanceSchedule);
    }

    @Test
    void shouldThrowExceptionWhenMaintenanceScheduleNotFound() {
        Integer id = 99;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MaintenanceScheduleNotFoundException.class, () -> service.getMaintenanceScheduleById(id));

        verify(repository).findById(id);
    }

    @Test
    void shouldReturnOnlyMaintenanceScheduleWithMonthlyMaintenanceForRequestedYear_whenScheduleHaveMultipleYears(){
        Integer year = 2024;

        MonthlyMaintenanceType monthlyMaintenanceType = new MonthlyMaintenanceType();
        monthlyMaintenanceType.setQuantity(1);
        monthlyMaintenanceType.setMaintenanceTypeEnum(MaintenanceTypeEnum.CA);

        MonthlyMaintenance mm2024 = new MonthlyMaintenance();
        mm2024.setYear(2024);
        mm2024.setMonth(1);
        mm2024.setMaintenanceTypes(List.of(monthlyMaintenanceType));

        MonthlyMaintenance mm2023 = new MonthlyMaintenance();
        mm2023.setYear(2023);
        mm2023.setMonth(1);
        mm2023.setMaintenanceTypes(List.of(monthlyMaintenanceType));

        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
        maintenanceSchedule.setId(1);
        maintenanceSchedule.setMonthlyMaintenances(List.of(mm2023, mm2024));

        List<MaintenanceSchedule> schedules = List.of(maintenanceSchedule);

        //DTOs
        MonthlyMaintenanceTypeResponse monthlyMaintenanceTypeResponse = new MonthlyMaintenanceTypeResponse();
        monthlyMaintenanceTypeResponse.setQuantity(1);
        monthlyMaintenanceTypeResponse.setMaintenanceType(MaintenanceTypeEnum.CA.toString());

        MonthlyMaintenanceResponse mmr2024 = new MonthlyMaintenanceResponse();
        mmr2024.setMaintenances(List.of(monthlyMaintenanceTypeResponse));
        mmr2024.setYear(2024);
        mmr2024.setMonth(1);

        MonthlyMaintenanceResponse mmr2023 = new MonthlyMaintenanceResponse();
        mmr2023.setMaintenances(List.of(monthlyMaintenanceTypeResponse));
        mmr2023.setYear(2024);
        mmr2023.setMonth(1);

        MaintenanceScheduleResponse maintenanceScheduleResponse = new MaintenanceScheduleResponse();
        maintenanceScheduleResponse.setId(1);
        maintenanceScheduleResponse.setMonthlyMaintenances(List.of(mmr2023, mmr2024));

        when(repository.findByYear(year)).thenReturn(schedules);
        when(mapper.buildMaintenanceScheduleResponse(maintenanceSchedule)).thenReturn(maintenanceScheduleResponse);

        var result = service.getMaintenanceScheduleByYear(year);

        assertEquals(1, result.size());
        assertEquals(maintenanceScheduleResponse, result.get(0));
        assertEquals(2024, result.get(0).getMonthlyMaintenances().get(0).getYear());

        verify(repository).findByYear(year);
        verify(mapper).buildMaintenanceScheduleResponse(maintenanceSchedule);
    }

    @Test
    void shouldThrowAnExceptionWhenThereAreNoMonthlyMaintenancesForTheRequestedYear(){
        Integer year = 2030;

        when(repository.findByYear(year)).thenReturn(List.of());

        var exception = assertThrows(MaintenanceScheduleNotFoundException.class, () -> service.getMaintenanceScheduleByYear(year));

        assertEquals("there is no maintenances for this year", exception.getMessage());

        verify(repository).findByYear(year);
    }

    @Test
    void shouldThrowAnExceptionWhenMaintenanceScheduleNotFound(){
        Integer id = 99;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MaintenanceScheduleNotFoundException.class,
                () -> service.getMaintenanceScheduleById(id));

        verify(repository).findById(id);
    }

    @Test
    void shouldDeleteMaintenanceScheduleById() {
        Integer id = 1;

        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
        maintenanceSchedule.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(maintenanceSchedule));

        String result = service.deleteMaintenanceSchedule(id);

        assertEquals("Maintenance schedule deleted", result);

        verify(repository).findById(id);
        verify(repository).delete(maintenanceSchedule);
    }
}
