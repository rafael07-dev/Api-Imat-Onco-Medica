package com.imat.oncomedica.inventory_management.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Table(name = "personal_mantenimiento")
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceStaffEntity {

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

    private String signaturePath;

    @OneToMany(mappedBy = "maintenanceStaff", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderEntity> orderList;

}
