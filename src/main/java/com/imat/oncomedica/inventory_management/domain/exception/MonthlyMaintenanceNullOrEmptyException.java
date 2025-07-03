package com.imat.oncomedica.inventory_management.domain.exception;

public class MonthlyMaintenanceNullOrEmptyException extends RuntimeException{

    public MonthlyMaintenanceNullOrEmptyException(){
        super("should have at least one maintenance");
    }

    public MonthlyMaintenanceNullOrEmptyException(String message){
        super(message);
    }
}
