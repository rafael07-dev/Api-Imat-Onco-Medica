package com.imat.oncomedica.inventory_management.domain.exception;

public class ReportPdfGeneratorException extends RuntimeException{

    public ReportPdfGeneratorException (){
        super("Error generating pdf");
    }
}
