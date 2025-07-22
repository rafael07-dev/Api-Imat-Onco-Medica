package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.imat.oncomedica.inventory_management.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private MaintenanceStaffEntity maintenanceStaff;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private EquipmentEntity equipment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "maintenance_id")
    private MaintenanceEntity maintenance;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
