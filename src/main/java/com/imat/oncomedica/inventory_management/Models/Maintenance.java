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

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff maintenanceStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_schedule_id")
    private MaintenanceSchedule maintenanceSchedules;

    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @Temporal(TemporalType.DATE)
    private Date dateOfCompletion;

    private String type;

    private Date timeUsed;

    private String observations;

}
