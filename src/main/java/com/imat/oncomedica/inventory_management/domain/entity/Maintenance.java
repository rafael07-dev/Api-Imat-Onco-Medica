package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "mantenimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff maintenanceStaff;

    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    private Date startDate;

    private Date startTime;

    private Date deliveryDate;

    private Date deliveryTime;

    private Date timeUsed;

    private Date registrationDate;

    @Temporal(TemporalType.DATE)
    private Date dateOfCompletion;

    private String type;

    private String observations;

    private String staffObservations;
    private boolean done;

    private String evidenceImg;

}
