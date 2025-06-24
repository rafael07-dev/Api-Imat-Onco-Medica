package com.imat.oncomedica.inventory_management.domain.exception;

public class MaintenanceNotFoundException extends RuntimeException {

    public MaintenanceNotFoundException() {
        super("Maintenance not found");
    }

    public MaintenanceNotFoundException(Integer id) {
        super("Maintenance not found with id " + id);
    }
}
