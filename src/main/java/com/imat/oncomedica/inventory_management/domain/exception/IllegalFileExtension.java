package com.imat.oncomedica.inventory_management.domain.exception;

public class IllegalFileExtension extends RuntimeException {
    public IllegalFileExtension(String message) {
        super(message);
    }
}
