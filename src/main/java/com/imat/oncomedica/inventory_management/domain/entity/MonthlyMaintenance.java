package com.imat.oncomedica.inventory_management.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serializable;
import java.util.Map;

@Entity
@Data
public class MonthlyMaintenance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private MaintenanceSchedule maintenanceSchedule;

    private String month;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Integer> maintenanceType;

}
