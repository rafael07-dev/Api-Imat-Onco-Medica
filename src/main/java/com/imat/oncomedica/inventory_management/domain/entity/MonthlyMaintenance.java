package com.imat.oncomedica.inventory_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serializable;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyMaintenance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonIgnore
    private MaintenanceSchedule maintenanceSchedule;

    private String month;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Integer> maintenanceType;

}
