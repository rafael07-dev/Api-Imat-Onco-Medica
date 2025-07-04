package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.domain.model.Order;

public interface ReportService {

    void generateOderReport(Order order);
}
