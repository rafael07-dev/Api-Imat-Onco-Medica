package com.imat.oncomedica.inventory_management.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "personal_mantenimiento")
@Data
@NoArgsConstructor
public class MaintenanceStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String firstName;

    @Column(name = "apellido", nullable = false)
    private String lastName;

    private String occupation;

    @Column(nullable = false)
    private boolean availability; // "available" or "busy"

    @Column(name = "mantenimientos_completados")
    private Integer maintenanceCompleted;

}
