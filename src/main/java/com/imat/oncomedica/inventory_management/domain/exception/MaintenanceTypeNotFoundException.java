package com.imat.oncomedica.inventory_management.domain.exception;


public class MaintenanceTypeNotFoundException extends RuntimeException{

    public MaintenanceTypeNotFoundException(){
        super("Maintenance Type Not Found");
    }
    public MaintenanceTypeNotFoundException(String message) {
        super(message);
    }
}
