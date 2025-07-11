package com.imat.oncomedica.inventory_management.domain.exception;

public class MaintenanceStaffNotFound extends RuntimeException{
    public MaintenanceStaffNotFound() {
        super("Maintenance Staff Not Found");
    }

    public MaintenanceStaffNotFound(Integer id) {
        super("Maintenance Staff Not Found with id " + id);
    }

    public MaintenanceStaffNotFound(String message) {
        super(message);
    }
}
