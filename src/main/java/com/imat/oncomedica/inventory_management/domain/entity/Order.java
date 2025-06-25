package com.imat.oncomedica.inventory_management.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaff maintenanceStaff;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
