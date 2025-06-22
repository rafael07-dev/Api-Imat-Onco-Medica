package com.imat.oncomedica.inventory_management.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal_mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String firstName;

    @Column(name = "apellido", nullable = false)
    private String lastName;

    private String email;

    private String occupation;

    @Column(nullable = false)
    private boolean availability; // "available" or "busy"

    @Column(name = "mantenimientos_completados")
    private Integer maintenanceCompleted;

}
