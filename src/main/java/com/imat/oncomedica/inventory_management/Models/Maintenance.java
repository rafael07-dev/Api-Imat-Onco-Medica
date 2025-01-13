package com.imat.oncomedica.inventory_management.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "mantenimientos")
@Data
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private String inventoryCode;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff maintenanceStaff;

    private String name;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    private Date startDate;

    private Date startTime;

    private Date deliveryDate;

    private Date timeUsed;

    private Date registrationDate;

    @Temporal(TemporalType.DATE)
    private Date dateOfCompletion;

    private String type;

    private String observations;

}
