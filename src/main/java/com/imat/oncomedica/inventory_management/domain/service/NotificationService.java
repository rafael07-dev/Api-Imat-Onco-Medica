package com.imat.oncomedica.inventory_management.domain.service;

import com.imat.oncomedica.inventory_management.domain.model.Maintenance;

public interface NotificationService {

    void notifyMaintenanceAssigned(Maintenance maintenance);
}
