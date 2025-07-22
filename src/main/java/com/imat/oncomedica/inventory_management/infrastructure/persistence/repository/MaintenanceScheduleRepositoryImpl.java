package com.imat.oncomedica.inventory_management.infrastructure.persistence.repository;

import com.imat.oncomedica.inventory_management.domain.model.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.repository.MaintenanceScheduleRepository;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.builder.MaintenanceSchedulePersistenceBuilder;
import com.imat.oncomedica.inventory_management.infrastructure.persistence.mapper.MaintenanceSchedulePersistenceMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MaintenanceScheduleRepositoryImpl implements MaintenanceScheduleRepository {

    private final SpringDataMaintenanceScheduleRepository maintenanceScheduleRepository;
    private final MaintenanceSchedulePersistenceBuilder maintenanceSchedulePersistenceBuilder;
    private final MaintenanceSchedulePersistenceMapper maintenanceSchedulePersistenceMapper;

    public MaintenanceScheduleRepositoryImpl(SpringDataMaintenanceScheduleRepository maintenanceScheduleRepository, MaintenanceSchedulePersistenceBuilder maintenanceSchedulePersistenceBuilder, MaintenanceSchedulePersistenceMapper maintenanceSchedulePersistenceMapper) {
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
        this.maintenanceSchedulePersistenceBuilder = maintenanceSchedulePersistenceBuilder;
        this.maintenanceSchedulePersistenceMapper = maintenanceSchedulePersistenceMapper;
    }


    @Override
    public List<MaintenanceSchedule> findAll() {
        return maintenanceScheduleRepository.findAll()
                .stream()
                .map(maintenanceSchedulePersistenceBuilder::build)
                .toList();
    }

    @Override
    public Optional<MaintenanceSchedule> findById(Integer id) {

        return maintenanceScheduleRepository.findById(id)
                .map(maintenanceSchedulePersistenceBuilder::build);
    }

    @Override
    public MaintenanceSchedule save(MaintenanceSchedule maintenanceSchedule) {
        var scheduleEntity = maintenanceSchedulePersistenceMapper.toEntity(maintenanceSchedule);
        var scheduleSaved = maintenanceScheduleRepository.save(scheduleEntity);
        return maintenanceSchedulePersistenceMapper.toModel(scheduleSaved);
    }

    @Override
    public void delete(MaintenanceSchedule maintenanceSchedule) {

    }

    @Override
    public List<MaintenanceSchedule> findByYear(Integer year) {
        return maintenanceScheduleRepository.findByYear(year)
                .stream()
                .map(maintenanceSchedulePersistenceBuilder::build)
                .toList();
    }

    @Override
    public Optional<MaintenanceSchedule> findByEquipmentId(Integer equipmentId) {
        return maintenanceScheduleRepository.findMaintenanceScheduleByEquipment_Id(equipmentId)
                .map(maintenanceSchedulePersistenceBuilder::build);
    }
}
