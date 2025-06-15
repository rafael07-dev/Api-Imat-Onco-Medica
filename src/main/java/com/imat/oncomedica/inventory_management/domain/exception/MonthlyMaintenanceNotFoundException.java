package com.imat.oncomedica.inventory_management.domain.exception;

public class MonthlyMaintenanceNotFoundException extends RuntimeException {
    public MonthlyMaintenanceNotFoundException() {
        super("Monthly Maintenance Not Found");
    }

    public MonthlyMaintenanceNotFoundException(Integer id) {
        super("Monthly Maintenance Not Found with id " + id);
    }
}
