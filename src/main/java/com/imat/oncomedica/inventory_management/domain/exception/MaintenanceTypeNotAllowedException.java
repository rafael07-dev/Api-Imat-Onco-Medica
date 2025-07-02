package com.imat.oncomedica.inventory_management.domain.exception;

public class MaintenanceTypeNotAllowedException extends RuntimeException{
    public MaintenanceTypeNotAllowedException() {
        super("Maintenance type not allowed");
    }

    public MaintenanceTypeNotAllowedException(String maintenanceType) {
        super("Maintenance type not allowed " + maintenanceType);
    }
}
