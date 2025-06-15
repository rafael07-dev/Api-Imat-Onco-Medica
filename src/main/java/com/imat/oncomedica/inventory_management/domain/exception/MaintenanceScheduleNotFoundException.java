package com.imat.oncomedica.inventory_management.domain.exception;

public class MaintenanceScheduleNotFoundException extends RuntimeException {
    public MaintenanceScheduleNotFoundException() {
        super("Maintenance schedule not found");
    }

    public MaintenanceScheduleNotFoundException(Integer id) {
        super("Maintenance schedule not found with id " + id);
    }
}
