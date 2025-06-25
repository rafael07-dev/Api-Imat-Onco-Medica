package com.imat.oncomedica.inventory_management.domain.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String m){
        super(m);
    }

    public OrderNotFoundException(Integer id){
        super("Order not found exception with id: " + id);
    }
}
