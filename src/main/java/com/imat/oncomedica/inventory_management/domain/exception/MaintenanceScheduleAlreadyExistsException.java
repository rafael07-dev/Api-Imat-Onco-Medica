package com.imat.oncomedica.inventory_management.domain.exception;

public class MaintenanceScheduleAlreadyExistsException extends RuntimeException {
    public MaintenanceScheduleAlreadyExistsException() {
        super("Maintenance schedule already exists");
    }

    public MaintenanceScheduleAlreadyExistsException(Integer id) {
        super("Maintenance schedule already exists with id " + id);
    }
}
