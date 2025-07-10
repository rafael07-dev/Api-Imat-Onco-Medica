package com.imat.oncomedica.inventory_management.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceStaff {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String occupation;

    private boolean availability; // "available" or "busy"

    private Integer maintenanceCompleted;

    private String signaturePath;

    private List<Order> orderList;

}
