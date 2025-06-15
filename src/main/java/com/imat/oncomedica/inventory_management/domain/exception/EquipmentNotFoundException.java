package com.imat.oncomedica.inventory_management.domain.exception;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException() {
        super("Equipment not found");
    }

    public EquipmentNotFoundException(Integer id) {
        super("Equipment not found with id " + id);
    }
}
