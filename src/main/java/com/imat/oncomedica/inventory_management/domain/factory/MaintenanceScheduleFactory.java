package com.imat.oncomedica.inventory_management.domain.factory;

import com.imat.oncomedica.inventory_management.domain.entity.Equipment;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceSchedule;
import com.imat.oncomedica.inventory_management.domain.entity.MaintenanceStaff;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class MaintenanceScheduleFactory {

    public MaintenanceSchedule create(Equipment equipment, MaintenanceStaff staff) {
        var maintenanceSchedule = new MaintenanceSchedule();

        maintenanceSchedule.setEquipment(equipment);
        maintenanceSchedule.setResponsible(staff);
        maintenanceSchedule.setMonthlyMaintenances(new ArrayList<>());

        return maintenanceSchedule;
    }
}
